package WhiteboardUtil;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point implements Serializable {
    private int x;
    private int y;
}
