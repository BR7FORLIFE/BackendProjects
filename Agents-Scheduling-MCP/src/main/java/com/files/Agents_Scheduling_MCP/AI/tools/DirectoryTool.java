package com.files.Agents_Scheduling_MCP.AI.tools;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.files.Agents_Scheduling_MCP.AI.tools.structureRequest.DirectoryToolParam;

/**
 * Herramienda capaz de poder ver las carpetas y archivos dentro de un
 * directorio
 */
@Component
public class DirectoryTool {

    @Tool(description = "List the folders within a specific directory")
    public List<String> listDirectories(DirectoryToolParam param) throws IOException {
        List<String> directories = new ArrayList<>();
        Path path = Paths.get(param.path());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, Files::isDirectory)) {
            for (Path subDirectory : stream) {
                directories.add(subDirectory.getFileName().toString());
            }

        }

        return directories;
    }

}
