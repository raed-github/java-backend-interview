package luxoft.codingchallange.filesystem;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class File {
    private long size;
    private String name;
    private LocalDateTime timeCreated;
    private Permissions permission;
}
