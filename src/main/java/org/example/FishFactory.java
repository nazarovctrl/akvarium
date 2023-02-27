package org.example;

public class FishFactory {

    public static Fish createFish(Aquarium aquarium) {
        int x = AquariumUtil.getRandom(AquariumUtil.length + 1);
        int y = AquariumUtil.getRandom(AquariumUtil.height + 1);
        int z = AquariumUtil.getRandom(AquariumUtil.width + 1);
        int life = AquariumUtil.getRandomBetween(5, 20);
        Gender gender = AquariumUtil.getRandomGender();

        return new Fish(x, y, z, life, gender, aquarium);
    }
}
