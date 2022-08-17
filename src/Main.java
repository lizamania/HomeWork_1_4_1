import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = new int[]{280, 270, 250, 305, 225, 290, 260, 220};
    public static int[] heroesDamage = new int[]{10, 15, 20, 13, 17, 25, 32, 21};
    public static String[] heroesAttackType = new String[]{"Physical", "Magical", "Kinetic", "Medik", "Golem", "Lucky", "Berserk", "Thor"};
    public static int roundNumber = 0;

    public Main() {
    }

    public static void main(String[] args) {
        printStatistics();

        while(!isGameFinished()) {
            playRound();
        }

    }

    public static void playRound() {
        ++roundNumber;
        chooseBossDefence();
        bossHits();
        heroesHit();
        berserkShoot();
        Deafen();
        printStatistics();
        medicHill();
        dodge();
        golemDefence();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void medicHill() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (i != 3) {
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    int[] var10000 = heroesHealth;
                    var10000[i] += 50;
                }

                String var10001 = heroesAttackType[i];
                System.out.println(" Medic healed " + var10001);
                break;
            }
        }

    }

    public static void golemDefence() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[7] > 0 && heroesHealth[i] > 0 && heroesHealth[7] != heroesHealth[i]) {
                int[] var10000 = heroesHealth;
                var10000[i] += bossDamage / 5;
                var10000 = heroesHealth;
                var10000[7] -= bossDamage / 5;
            }
        }

    }

    public static void dodge() {
        Random random = new Random();
        int randomavoidance = random.nextInt(4) + 1;
        switch (randomavoidance) {
            case 1:
                heroesHealth[6] += bossDamage;
                System.out.println("Lacky ");
            case 2:
            case 3:
            case 4:
            default:
        }
    }

    public static void Deafen() {
        Random random = new Random();
        boolean deafen = random.nextBoolean();
        if (deafen) {
            bossDamage = 0;
            System.out.println("Boss oglushon");
        } else {
            bossDamage = 50;
        }

    }

    public static void berserkShoot() {
        Random random = new Random();
        int randomDamage = random.nextInt(15) + 1;
        int any = random.nextInt(3) + 1;
        if (heroesHealth[4] > 0 && bossHealth > 0) {
            switch (any) {
                case 1:
                    heroesDamage[4] = heroesDamage[4] + bossDamage - randomDamage;
                    System.out.println("Берсерка урон критический");
                    System.out.println("Потери при увеличении урон а Берсерка " + randomDamage);
                    break;
                case 2:
                    bossDamage = 50;
                    break;
                case 3:
                    bossDamage = 50;
            }
        }

    }

    public static void bossHits() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] -= bossDamage;
                }
            }
        }

    }
    public static void heroesHit() {
        for(int i = 0; i < heroesDamage.length; ++i) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= heroesDamage[i] * coeff;
                    }

                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth -= heroesDamage[i];
                }
            }
        }

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        } else {
            boolean allHeroesDead = true;
            int[] var1 = heroesHealth;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                int healthOfCurrentHero = var1[var3];
                if (healthOfCurrentHero > 0) {
                    allHeroesDead = false;
                    break;
                }
            }

            if (allHeroesDead) {
                System.out.println("Boss won!!!");
            }

            return allHeroesDead;





        }
    }

    public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE START -------------");
        } else {
            System.out.println("ROUND " + roundNumber + " -------------");
        }

        System.out.println("Boss health: " + bossHealth + "; damage: " + bossDamage + "; defence: " + (bossDefence == null ? "No defence" : bossDefence));

        for(int i = 0; i < heroesHealth.length; ++i) {
            String var10001 = heroesAttackType[i];
            System.out.println(var10001 + " health: " + heroesHealth[i] + "; damage: " + heroesDamage[i]);
        }

    }
}
