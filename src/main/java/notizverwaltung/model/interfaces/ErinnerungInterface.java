package model.interfaces;

import java.util.Date;

public interface ErinnerungInterface {

    public Date getErinnterungsDatum();
    public void setErinnterungsDatum(Date datum);
    public boolean setIstErinnerungsdatumGesetzt();
    public boolean getIstErinnerungsdatumGesetzt();

}