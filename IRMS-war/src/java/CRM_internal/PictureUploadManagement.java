/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import CRM.entity.ResortPackage;
import CRM.session.PackageManagementSessionBeanLocal;
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
public class PictureUploadManagement {
    @EJB
    private PackageManagementSessionBeanLocal pmsbl;
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;
    private UploadedFile file;
    private ResortPackage pkg;


    public PictureUploadManagement() {
    }
    
    public void packageImageUpload(FileUploadEvent event) throws IOException{
        FacesMessage msg = new FacesMessage("Succesful package imgae upload", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        String pkgID=String.valueOf(getPkg().getId());
        System.err.println("Package Image Name : "+ pkg.getPicName());
        pmsbl.updatePackagePicture(pkgID);
        System.err.println("Package Image Name2: "+pkg.getPicName());
        
        String[] fileNameParts = event.getFile().getFileName().split("\\.");
        System.err.println(fileNameParts);
        File result = new File("//Users//zsy//GlassFish_Server//glassfish//domains//domain1//docroot//images//Packages //" + pkgID + "." + fileNameParts[1]);
        System.err.println("Test");
        pmsbl.setImageType(Long.valueOf(pkgID), fileNameParts[1]);
        System.err.println("current photo type: "+pkg.getImageType());
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

    public ResortPackage getPkg() {
       pkg=(ResortPackage) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pkg");
        return pkg;
    }

    public void setPkg(ResortPackage pkg) {
        this.pkg = pkg;
    }
}
