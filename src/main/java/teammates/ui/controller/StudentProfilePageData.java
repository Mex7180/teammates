package teammates.ui.controller;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.StudentProfileAttributes;
import teammates.common.util.Const;
import teammates.ui.template.StudentProfileEditBox;
import teammates.ui.template.StudentProfileUploadPhotoModal;

public class StudentProfilePageData extends PageData {

    private StudentProfileEditBox profileEditBox;
    private StudentProfileUploadPhotoModal uploadPhotoModal;
    
    public StudentProfilePageData(AccountAttributes account, String editPicture) {
        super(account);
        init(account, editPicture);
    }
    
    private void init(AccountAttributes account, String editPicture) {
        StudentProfileAttributes profile = account.studentProfile;
        String pictureUrl;
        if (profile.pictureKey.equals("")) {
            pictureUrl = Const.SystemParams.DEFAULT_PROFILE_PICTURE_PATH;
        } else {
            pictureUrl = Const.ActionURIs.STUDENT_PROFILE_PICTURE
                       + "?" + Const.ParamsNames.BLOB_KEY + "=" + profile.pictureKey
                       + "&" + Const.ParamsNames.USER_ID + "=" + account.googleId;
        }
        this.profileEditBox = new StudentProfileEditBox(account.name, editPicture, profile.shortName,
                                                        profile.email, profile.institute, profile.nationality,
                                                        profile.gender, profile.moreInfo, account.googleId,
                                                        pictureUrl);
        this.uploadPhotoModal = new StudentProfileUploadPhotoModal(account.googleId, pictureUrl, profile.pictureKey);

    }
    
    public StudentProfileEditBox getProfileEditBox() {
        return profileEditBox;
    }

    public StudentProfileUploadPhotoModal getUploadPhotoModal() {
        return uploadPhotoModal;
    }

}
