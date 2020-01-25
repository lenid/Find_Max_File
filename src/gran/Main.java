package gran;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

        static String PATH = "C:\\";
//    static String PATH = "E:\\test";

    static long Mb = 1048576;
    static long FILE_FILTER = 100 * Mb;
    static long FOLDER_FILTER = 500 * Mb;



    public static void main(String[] args) {
        File file = new File(PATH);
        List<File> files = getFiles(file);
        List<Folder> folders = getFolders(files);

        files.stream().filter(f -> f.length() > FILE_FILTER)
                .forEach(f -> System.out.println((f.length() / Mb ) + "Mb  -> " + f.getPath()));
        System.out.println("\n\n");
        folders.stream().filter(f -> f.getSize() > FOLDER_FILTER)
                .forEach(f -> System.out.println((f.getSize() / Mb ) + "Mb  -> " + f.getPath()));
    }

    static List<Folder> getFolders(List<File> files) {
        Map<String, Folder> folderMap = new HashMap<>();

        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            String parent = file.getParent();
            Folder folder = folderMap.get(parent);

            if (folder == null) {
                folder = new Folder();
                folder.setPath(parent);
            }

            folder.addSize(file.length());
            folderMap.put(parent, folder);
        }

        return folderMap.entrySet().stream()
                .map(e -> e.getValue())
                .sorted(Comparator.comparing(Folder::getSize).reversed()).collect(Collectors.toList());
    }


    static List<File> getFiles(File file) {
        if (file.isFile()) {
            return Arrays.asList(file);
        }

        List<File> res = new ArrayList<>();
        File[] files = file.listFiles();

        if (files == null) {
            return Arrays.asList();
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                res.add(files[i]);
            } else {
                res.addAll(getFiles(files[i]));
            }
        }

        return res.stream().sorted(Comparator.comparing(File::length).reversed()).collect(Collectors.toList());
    }

}
