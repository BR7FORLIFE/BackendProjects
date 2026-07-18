package com.files.Agents_Scheduling_MCP.tools;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.files.Agents_Scheduling_MCP.tools.interfaces.ToolInterface;

/**
 * Herramienda capaz de poder ver las carpetas y archivos dentro de un
 * directorio
 */
@Component
public class FileSystemTool implements ToolInterface {

    @Tool(description = "List the folders within a specific directory")
    public List<String> listDirectories(String param) throws IOException {
        List<String> directories = new ArrayList<>();
        Path path = Paths.get(param);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, Files::isDirectory)) {
            for (Path subDirectory : stream) {
                directories.add(subDirectory.getFileName().toString());
            }

        }

        return directories;
    }

}
