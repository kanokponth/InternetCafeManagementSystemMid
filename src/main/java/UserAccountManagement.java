public class UserAccountManagement {

    private int warning;
    private String warningMessage1,warningMessage2,warningMessage3,warningMessage,warnningMessageNull;


    public void checkNewPassword(String passwordInSystem, String oldPassword, String newPassword, String newPasswordConfirmation) {
        if (oldPassword.equals("")||newPassword.equals("")||newPasswordConfirmation.equals("")) {
            warnningMessageNull="โปรดกรอกข้อมูลให้ครบถ้วน";
            warningMessage1 = "";
            warningMessage = "";
            warningMessage2 = "";
            warningMessage3 = "";

        } else {
            if (passwordInSystem.equals(oldPassword)) {
                if (oldPassword.equals(newPassword)) {
                    System.out.println("รหัสใหม่ซ้ำกับรหัสเก่า");
                    warningMessage1 = "รหัสใหม่ซ้ำกับรหัสเก่า";
                    warningMessage = "";
                    warningMessage2 = "";
                    warningMessage3 = "";
                    warnningMessageNull="";

                    warning = 1;


                } else {
                    if (newPassword.equals(newPasswordConfirmation)) {
                        warning = 0;
                        warningMessage = "เปลี่ยนรหัสเรียบร้อย";
                        warningMessage1 = "";
                        warningMessage2 = "";
                        warningMessage3 = "";
                        warnningMessageNull="";

                    } else {
                        System.out.println("โปรดกรอกรหัสยืนยันให้ตรงกับรหัสใหม่");
                        warningMessage2 = "โปรดกรอกรหัสยืนยันให้ตรงกับรหัสใหม่";
                        warningMessage1 = "";
                        warningMessage = "";
                        warningMessage3 = "";
                        warnningMessageNull="";
                        warning = 2;

                    }
                }
            } else {
                System.out.println("โปรดกรอกรหัสให้ตรงกับรหัสในระบบ");
                warningMessage3 = "โปรดกรอกรหัสให้ตรงกับรหัสในระบบ";
                warningMessage = "";
                warningMessage2 = "";
                warningMessage1 = "";
                warnningMessageNull="";
                warning = 3;

            }
        }
    }


    public int getWarning() {
        return warning;
    }

    public String getWarningMessage1() {
        return warningMessage1;
    }

    public String getWarningMessage2() {
        return warningMessage2;
    }

    public String getWarningMessage3() {
        return warningMessage3;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public String getWarnningMessageNull() {
        return warnningMessageNull;
    }
}
