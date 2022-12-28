import javax.xml.bind.annotation.*;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"r_OBJECT_ID","JL" })
public class Jiaolong {
    @XmlElement
    private String JL;
    private String R_OBJECT_ID;

    public void setJl(String jl) {
        this.JL = jl;
    }
    @XmlElement(name = "rid")
    public String getR_OBJECT_ID() {
        return R_OBJECT_ID;
    }

    public void setR_OBJECT_ID(String r_OBJECT_ID) {
        R_OBJECT_ID = r_OBJECT_ID;
    }
}
