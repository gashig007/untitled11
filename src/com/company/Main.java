package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 300, 1000, 230, 240, 250};
    public static int[] heroesDamage = {15, 20, 25, 0, 5, 10, 20, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;
    private static int golemHit = bossDamage / 5;

    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 4); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
//        System.out.println(thor());
        changeBossDefence();
        bossHits();
        heroesHit();
        medic();
//        berserk();
//        lucky();
//        golem();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    if (i == 4) {
                        System.out.println("Golem has taken " + golemHit + "from boss");
                        heroesHealth[4] = heroesHealth[4] -(bossDamage + (golemHit * heroesHealth.length - 1));
                    }else {
                        if (heroesHealth[4] > 0) {
                            heroesHealth[i] = heroesHealth[i] - bossDamage + golemHit;
                        } else {
                            heroesHealth[4] = 0;
                            heroesHealth[i] = heroesHealth[i] - bossDamage;
                        }
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random ramdom1 = new Random();
            if (heroesHealth[1] > 0 && heroesHealth[1] < 100 && heroesHealth[3] < 0) {
                heroesHealth[1] = ramdom1.nextInt(20) + heroesHealth[1];
                break;
            } else if (heroesHealth[2] > 0 && heroesHealth[2] < 100 && heroesHealth[3] < 0) {
                heroesHealth[2] = ramdom1.nextInt(20) + heroesHealth[2];
                break;
            } else if (heroesHealth[0] > 0 && heroesHealth[0] < 100 && heroesHealth[3] < 0) {
                heroesHealth[0] = ramdom1.nextInt(20) + heroesHealth[0];
                break;
            } else if (heroesHealth[i] < 0) {
                boolean random1 = false;
            }
        }
    }

    public static void golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 4) {
                heroesHealth[4] = heroesHealth[4] - bossDamage * 1 / 5;
            }
            heroesHealth[i] = 1 / 5 * bossDamage;
        }
    }

    public static void lucky() {
        for (int i = 4; i > heroesHealth[3]; i--) {
            Random random2 = new Random();
            int randomIndex = random2.nextInt(bossDamage);
        }
    }

    public static void berserk() {
        for (int i = 5; i > heroesHealth[4]; i--) {
            Random random3 = new Random();
            int randomIndex = heroesDamage[5] + random3.nextInt(50);
            heroesHealth[5] = bossDamage - randomIndex;
            heroesDamage[5] = randomIndex + heroesDamage[5];
        }
    }

    public static boolean thor() {
        Random random3 = new Random();
        return random3.nextBoolean();
    }
}


