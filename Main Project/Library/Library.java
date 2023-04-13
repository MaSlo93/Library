package Library;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import Pojo.Literature;

public class Library {
    public HashMap<String, Literature> library = new HashMap<>();

    public void addLiterature(Literature literature) {
        this.library.put(literature.getName(), literature.clone());
    }

    public Literature retrieveLiterature(String name) {
        return library.get(name);

    }

    public void deleteLiterature(Literature literature) {
        this.library.remove(literature.getName());
    }

    public void updateLiterature(Literature literature) {
        this.library.put(literature.getName(), literature.clone());
    }

    @Override
    public String toString() {
        String str = library.entrySet().stream().map(x -> "\n" + x.getValue())
                .collect(Collectors.joining(""));
        return str;
    }

    public String filter(String string) {
        List<String> list = library.entrySet().stream().map(x -> "\n" + x.getValue())
                .collect(Collectors.toList());
        String filtered = list.stream().filter(x -> x.toLowerCase().contains(string.toLowerCase()))
                .collect(Collectors.joining(""));
        return filtered;
    }

    public boolean contains(String key) {
        return this.library.containsKey(key);
    }
}