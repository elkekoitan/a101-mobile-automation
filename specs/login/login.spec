# Login Specification
Tags: login

## Successful Login with Valid Phone Number
Tags: smoke, positive
* Launch A101 mobile application
* Click login button
* Enter phone number "5551234567"
* Click continue button
* Enter OTP code
* Verify user is logged in successfully

## Login with Empty Phone Number
Tags: negative
* Launch A101 mobile application
* Click login button
* Leave phone number empty
* Click continue button
* Verify error message "Lütfen telefon numaranızı giriniz"

## Login with Invalid Phone Number Format
Tags: negative
* Launch A101 mobile application
* Click login button
* Enter phone number "123"
* Click continue button
* Verify error message "Geçerli bir telefon numarası giriniz"

## Login with Invalid OTP Code
Tags: negative
* Launch A101 mobile application
* Click login button
* Enter phone number "5551234567"
* Click continue button
* Enter OTP code "000000"
* Verify error message "Geçersiz doğrulama kodu"
