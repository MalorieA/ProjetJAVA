package fr.chalodss.spaceinvaders.classes;

import java.util.LinkedList;
import java.util.List;

import static fr.chalodss.spaceinvaders.utils.Images.img;
import static fr.chalodss.spaceinvaders.utils.Images.img1;
import static fr.chalodss.spaceinvaders.classes.Controller.invWidth;

/**
 * class pour initialiser les alias
 *
 * */
public final class Init {

    private Init() {

    }


/**
 * fonction qui permet de generer les lignes des alias
 * @return elle retourne une liste qui contient tous les alias chacun a sa position spécifique
 * */
    public static List<Entity> initInvaders(int x, int y) {
        List<Entity> list = new LinkedList<>();
        for (var i = 1; i <= 65; i++) {
            if (i % 11== 0) {
                x  = invWidth;
                y += 60;
            } else if (i <= 32) {
                list.add(new Entity(x, y, img1));
            } else if (i <= 65) {
                list.add(new Entity(x, y, img));
            }
            x+=(i % 11 != 0) ? 60 : 0;
        }
        return list;}



    }
