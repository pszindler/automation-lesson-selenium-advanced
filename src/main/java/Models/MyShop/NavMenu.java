package Models.MyShop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NavMenu {
    private String categoryName;
    private String href;
    private int depth;
    private String id;
    private NavMenu parent;

    @Override
    public String toString() {
        return "NavMenu{" +
                "categoryName='" + categoryName + '\'' +
                ", href='" + href + '\'' +
                ", depth=" + depth +
                ", id='" + id + '\'' +
                ", parent=" + parent +
                '}';
    }
}
