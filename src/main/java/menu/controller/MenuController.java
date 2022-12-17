package menu.controller;

import menu.domain.Coach;
import menu.domain.MenuList;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuController {

    private final MenuService menuService;

    public MenuController() {
        List<Coach> coachInfo = getCoachInfo();
        menuService = new MenuService(coachInfo);
    }

    public void start(){
        try {
            OutputView.recommendStart();
            getRecommendMenu();
            OutputView.printResult(menuService.getCoach());
            OutputView.quit();
        }
        catch (IllegalArgumentException | IllegalStateException exception){
            System.out.println("[ERROR]" + exception.getMessage());
        }
        catch (NullPointerException exception){
            System.out.println("[ERROR]" + "프로그램이 정상작동하지 않았습니다.");
        }
    }

    public void getRecommendMenu(){
        for(int i=0; i<5; i++){
            menuService.recommend();
        }
    }

    public List<Coach> getCoachInfo(){
        String names = InputView.getCoachInfo();
        List<String> name = Arrays.stream(names.split(",")).collect(Collectors.toList());
        nameValidation(name);
        List<Coach> coaches = name.stream().map(Coach::new).collect(Collectors.toList());
        for(Coach coach : coaches){
            String hateMenu = InputView.getHateMenu(coach);
             coach.setHateMenu(hateMenuValidation(hateMenu));
        }
        return coaches;
    }

    private List<String> hateMenuValidation(String hateMenu) {
        String[] s = hateMenu.split(",");
        List<String> hateMenus = Arrays.stream(hateMenu.split(",")).collect(Collectors.toList());
        if(2 < hateMenus.size()){
            throw new IllegalArgumentException("싫어하는 메뉴의 수는 2개를 넘을 수 없습니다.");
        }
        for(String menu : hateMenus){
            if(!MenuList.find(menu)){
                throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
            }
        }
        return hateMenus;
    }

    private void nameValidation(List<String> name) {
        if(name.size() < 2 || 5 < name.size()){
            throw new IllegalArgumentException("코치의 수는 2~5명 사이에 존재해야 합니다.");
        }
    }
}
