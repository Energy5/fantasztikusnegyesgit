package hu.fantasztik.szofttech;

import hu.fantasztik.szofttech.state.WindowMenu;

public class Boot {
    public static void main(String[] args) throws NullPointerException {
        try {
                new WindowMenu();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}