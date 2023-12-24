package com.accolite.Payment;

import com.accolite.Users.UsersEntity;
import com.accolite.Users.UsersRepository;
import com.accolite.Users.UsersService;
import com.accolite.Wallet.WalletEntity;
import com.accolite.Wallet.WalletRepository;
import com.accolite.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public ResponseEntity<String > savePayment(PaymentEntity payment) {
        if (payment != null) {
            Long userId = payment.getUserId();
            Long vendorId = payment.getVendorId();
            UsersEntity userDTO = usersRepository.findById(userId).get();
            UsersEntity vendorDTO = usersRepository.findById(vendorId).get();

            if (userDTO != null && vendorDTO != null) {
                if (userDTO.getRole().ordinal() == 2 && vendorDTO.getRole().ordinal() == 1 && userDTO.getStatus() == 1 && vendorDTO.getStatus() == 1) {
                    WalletEntity userWallet = walletRepository.findById(userId).get();
                    if (userWallet.getStatus() == 1 && userWallet.getAmount()>=payment.getAmount()) {
                        List<Long> uniqueCodes = userWallet.getUniqueCodes();
                        boolean distanceBetweenVendor = checkFlagged(payment, vendorDTO, uniqueCodes, payment.getUniqueCode());
                        if (distanceBetweenVendor ) {

                            paymentRepository.save(payment);
                            return new ResponseEntity<>("Payment Added. Waiting for Approval from Admin",HttpStatus.CREATED);
                        } else {
                            payment.setStatus((byte) 3);
                            paymentRepository.save(payment);
                            return new ResponseEntity<>("You are far away from Vendor.But Payment Added",HttpStatus.CREATED);
                        }
                    } else
                        return new ResponseEntity<>("Activate Wallet Or Check Wallet Balance User : " + userId,HttpStatus.NOT_MODIFIED);
                }
            }
        }

        return new ResponseEntity<>("Payment Not Added",HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<String> approvePayment(Long id, Byte option) {
        PaymentEntity payment = paymentRepository.findById(id).orElse(null);
        if (payment != null && payment.getStatus()==0) {
            if (payment.getStatus() == 3) {
                walletService.updateWallet(payment.getUserId(), payment.getAmount());
            } else {
                if (option == 1) {
                    payment.setStatus((byte) 1);
                    walletService.updateWallet(payment.getVendorId(), payment.getUserId(), payment.getAmount());
                } else
                    payment.setStatus((byte) 2);
            }
            paymentRepository.save(payment);
            return new ResponseEntity<>("Updated in Payment", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Not Found Or Already Payment Approved",HttpStatus.NOT_FOUND);
    }

    private boolean checkFlagged(PaymentEntity paymentDTO, UsersEntity vendorDTO, List<Long> uniqueCodes, Long uniqueCode) {

        if (calculateDistance(paymentDTO.getLattitude(), paymentDTO.getLongitude(), vendorDTO.getLattitude(), vendorDTO.getLongitude()) <= 500) {
            return uniqueCodes.contains(uniqueCode);
        }
        return false;
    }

    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int EARTH_RADIUS = 6371;
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = Math.sqrt(x * x + y * y) * EARTH_RADIUS;

        return distance;
    }

}
