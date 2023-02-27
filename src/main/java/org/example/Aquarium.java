package org.example;

import java.util.LinkedList;

public class Aquarium {
    private LinkedList<Fish> fishLinkedList = new LinkedList<>();


    public void start() {
        initMaleName();
        initFemaleName();

        int size = AquariumUtil.getRandomBetween(5, 6);
        for (int i = 0; i < size; i++) {
            Fish fish = FishFactory.createFish(this);
            if (fish.getGender().equals(Gender.MALE)) {
                fish.setFishName(AquariumUtil.getRandomMaleName());
            } else {
                fish.setFishName(AquariumUtil.getRandomFemaleName());
            }

            fishLinkedList.add(fish);
        }

        for (Fish fish : fishLinkedList) {
            fish.start();
        }
    }

    private void initMaleName() {
        if (!AquariumUtil.maleNameList.isEmpty()) {
            return;
        }

        AquariumUtil.maleNameList.add("Nemo");
        AquariumUtil.maleNameList.add("Crud");
        AquariumUtil.maleNameList.add("Jek");
        AquariumUtil.maleNameList.add("Flip");
        AquariumUtil.maleNameList.add("Artur");
        AquariumUtil.maleNameList.add("Snowy");
    }

    private void initFemaleName() {
        if (!AquariumUtil.femaleNameList.isEmpty()) {
            return;
        }

        AquariumUtil.femaleNameList.add("Katy");
        AquariumUtil.femaleNameList.add("Safia");
        AquariumUtil.femaleNameList.add("Pink");
        AquariumUtil.femaleNameList.add("Elizabet");
        AquariumUtil.femaleNameList.add("Adel");
        AquariumUtil.femaleNameList.add("Adelina");
    }

    public void checkForCollision(Fish fish) {
        synchronized (fishLinkedList) {
            if (fishLinkedList.size() < AquariumUtil.totalAmount) {


                Fish babyFish = null;
                for (Fish f : fishLinkedList) {
                    NameDTO nameDTO;
                    if ((nameDTO = fish.collision(f)) != null) {

                        babyFish = FishFactory.createFish(this);
                        babyFish.setFishName(nameDTO);

                        String str = String.format("Collision: Fish1 - {%s}, Fish2 - {%s}, baby - {%s} , gender - {%s}",
                                fish.getFishName(), f.getFishName(), babyFish.getFishName(), babyFish.getGender());
                        System.out.println(str);
                        break;
                    }
                }
                if (babyFish != null) {
                    babyFish.start();
                    fishLinkedList.add(babyFish);
                }
            }
            printDetail();
        }
    }



    public synchronized void printDetail() {

        int totalSize = fishLinkedList.size();
        int m = 0;
        int f = 0;
        for (Fish fish : fishLinkedList) {
            if (fish.getGender().equals(Gender.MALE)) {
                m++;
            } else {
                f++;
            }
        }
        System.out.println("==============================");
        System.out.println("Total Count:" + totalSize);
        System.out.println("Male:" + m);
        System.out.println("Female:" + f);
        System.out.println("==============================");
    }

    public void removeFish(Fish fish) {
        synchronized (fishLinkedList) {
            fishLinkedList.remove(fish);
            System.out.println("------------------------");
            System.out.println("Fish dead: " + fish);
            System.out.println("------------------------");
            printDetail();

        }
    }
}
