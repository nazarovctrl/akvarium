package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fish extends Thread {
    private int coordinateX;
    private int coordinateY;
    private int coordinateZ;
    private String fishName;
    private int life;
    private Gender gender;
    private Aquarium aquarium;


    public Fish(int coordinateX, int coordinateY, int coordinateZ, int life, Gender gender, Aquarium aquarium) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
        this.life = life;
        this.gender = gender;
        this.aquarium = aquarium;
    }

    @Override
    public void run() {
        while (life > 0) {
            move();
            try {
                Thread.sleep(1000);
                life--;
                aquarium.checkForCollision(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        aquarium.removeFish(this);
    }

    public void move() {

        int direction = AquariumUtil.getRandom(7);

        switch (direction) {
            case 0:
                if (coordinateY < AquariumUtil.height) {
                    coordinateY++;
                }
                break;
            case 1:
                if (coordinateX < AquariumUtil.length) {
                    coordinateX++;
                }
                break;
            case 2:
                if (coordinateZ < AquariumUtil.width) {
                    coordinateZ++;
                }
                break;

            case 3:
                if (coordinateY > 0) {
                    coordinateY--;
                }
                break;
            case 4:
                if (coordinateX > 0) {
                    coordinateX--;
                }
                break;
            case 5:
                if (coordinateZ > 0) {
                    coordinateZ--;
                }
                break;
        }

    }


    public String getFishName() {
        return fishName;
    }

    public void setFishName(NameDTO nameDTO) {
        if (this.getGender().equals(Gender.MALE)) {
            fishName = nameDTO.getFatherName();
        } else {
            fishName = nameDTO.getMotherName();
        }

        char[] chars = fishName.toCharArray();

        int num = 0;
        int k = 1;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                num = num * k + Character.getNumericValue(chars[i]);
                System.out.println(num);
                k = k * 10;
                fishName = fishName.substring(0, fishName.length() - 1);
            }
        }
        if (num == 0) {
            fishName = fishName + "1";
            return;
        }
        num++;

        fishName = fishName + num;

    }

    public void setFishName(String name) {
        this.fishName = name;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCoordinateZ() {
        return coordinateZ;
    }

    public void setCoordinateZ(int coordinateZ) {
        this.coordinateZ = coordinateZ;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Aquarium getAquarium() {
        return aquarium;
    }

    public void setAquarium(Aquarium aquarium) {
        this.aquarium = aquarium;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Fish{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", coordinateZ=" + coordinateZ +
                ", name='" + fishName + '\'' +
                ", life=" + life +
                ", gender=" + gender +
                '}';
    }

    public NameDTO collision(Fish f) {
        if (this.coordinateX != f.coordinateX || this.coordinateY != f.coordinateY || this.coordinateZ != f.coordinateZ || this.gender.equals(f.gender)) {
            return null;
        }

        NameDTO nameDTO;
        if (f.getGender().equals(Gender.MALE)) {
            nameDTO = new NameDTO(f.getFishName(), this.fishName);
        } else {
            nameDTO = new NameDTO(this.fishName, f.getFishName());
        }
        return nameDTO;

    }
}
