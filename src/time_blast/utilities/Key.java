package time_blast.utilities;

public class Key  {
    public boolean isPressed=false;
    String keyName="";
    Key(DefaultBinds bind){
        keyName = bind.name();
    }
    @Override
    public String toString(){return keyName;}
}
