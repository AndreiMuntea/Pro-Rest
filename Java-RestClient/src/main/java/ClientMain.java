import Domain.Trial;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrei on 2017-05-21.
 */
public class ClientMain {
    public static final String URL = "http://localhost:8080/trials";
    public static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        testInsert();
        testUpdate();
        testDelete();
    }

    static void testInsert() {
        List<Trial> all;
        Trial t = new Trial("nou", 1251);
        insert(t);
        all = getAll();
        all.forEach(System.out::println);
    }

    static void testDelete() {
        List<Trial> all;
        Trial t = new Trial("nou", 1251);
        delete(t.getId());
        all = getAll();
        all.forEach(System.out::println);
    }

    static void testUpdate(){
        List<Trial> all;
        Trial t = new Trial("nou", 1251);
        t.setDifficulty(999);
        update(t);
        all = getAll();
        all.forEach(System.out::println);
    }

    public static List<Trial> getAll() {
        return Arrays.asList(restTemplate.getForObject(URL, Trial[].class));
    }

    public static Trial getById(String id) {
        return restTemplate.getForObject(String.format("%s/%s", URL, id), Trial.class);
    }

    public static void insert(Trial t) {
        try {
            restTemplate.postForObject(URL, t, Trial.class);
        }catch (Exception e)
        {
            //ignored
        }
    }

    public static void update(Trial t) {
        restTemplate.put(URL, t, Trial.class);
    }

    public static void delete(String id) {
        restTemplate.delete(String.format("%s/%s", URL, id));
    }
}
