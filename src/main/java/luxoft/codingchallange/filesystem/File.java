package luxoft.codingchallange.filesystem;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class File {
    private long size;
    private String name;
    private LocalDateTime timeCreated;
    private Permissions permission;
}
