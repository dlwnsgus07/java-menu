package menu.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum MenuList {
    JAPAN(Category.JAPAN, List.of("규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼")),
    KOREA(Category.KOREA, List.of("김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음")),
    CHINA(Category.CHINA, List.of("깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채")),
    ASIA(Category.ASIA, List.of("팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜")),
    AMERICA(Category.AMERICA, List.of("라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니"))
    ;

    private Category category;
    private List<String> menus;

    MenuList(Category category, List<String> menus) {
        this.category = category;
        this.menus = menus;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getMenus() {
        return menus;
    }

    public static boolean find(String menu){
        List<List<String>> collect = Arrays.stream(values()).map(MenuList::getMenus).collect(Collectors.toList());
        for(List<String> menus : collect){
            for(String name : menus){
                if(Objects.equals(name, menu)){
                    return true;
                }
            }
        }
        return false;
    }
}
