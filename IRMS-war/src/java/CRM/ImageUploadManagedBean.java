/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.MemberAccount;
import CRM.session.MemberAccountManagementSessionBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class ImageUploadManagedBean {
    @EJB
    private MemberAccountManagementSessionBeanLocal mamsbl;
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;
    private UploadedFile file;
    private MemberAccount member;

    /**
     * Creates a new instance of ImageUploadManagedBean
     */
    public ImageUploadManagedBean() {
    }

    public void handleImageUpload(FileUploadEvent event)
            throws IOException {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        String memberId=String.valueOf(member.getId());
        System.err.println("Profile Photo Name : "+member.getMembership().getProfilePhoto());
//        mamsbl.EditProfilePhoto(memberId);
         System.err.println("Profile Photo Name2 : "+member.getMembership().getProfilePhoto());
               
        String[] fileNameParts = event.getFile().getFileName().split("\\.");
        System.err.println(fileNameParts);
        File result = new File("C:\\Users\\ARIEL CHENG\\Desktop\\IS3102\\projectt\\IRMS\\IRMS-war\\web\\resources\\cr\\images\\CRM\\customerPhoto\\" + memberId + "." + fileNameParts[1]);
        System.err.println("Test");
        InputStream is;
        FileOutputStream out = new FileOutputStream(result);
        try {
            int a;
            byte[] buffer = new byte[BUFFER_SIZE];
            is = event.getFile().getInputstream();
            while (true) {
                a = is.read(buffer);
                if (a < 0) {
                    break;
                }
                out.write(buffer, 0, a);
                out.flush();
            }
            is.close();
        } catch (Exception ex) {
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Image has been uploaded", ""));
    }

    public String getFolderToUpload() {
        return folderToUpload;
    }

    public void setFolderToUpload(String folderToUpload) {
        this.folderToUpload = folderToUpload;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public MemberAccount getMember() {
        return (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }
}
