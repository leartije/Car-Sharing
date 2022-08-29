package carsharing.services;

import carsharing.util.Msg;

import java.util.Scanner;

public interface CarSharingService<T, S, ID> {

    default void createEntity() {}

    default void createEntity(S s) {}

    default int chooseEntity() {
        return -1;
    }

    default int chooseEntity(S s) {
        return -1;}

    default void printAll(S s) {}

    T getByIdFromList(ID id);

    T getById(ID id);

    default void updateEntity(T t, String id) {}

    default int getInt(String input, int size) {
        try {
            int n = Integer.parseInt(input);
            if (n < 0 || n > size) {
                System.out.printf(Msg.INDEX_OUT_OF_BOUND, n);
                return -1;
            }
            return n;

        } catch (NumberFormatException e) {
            System.out.printf(Msg.WRONG_INPUT, input);
            return -1;
        }
    }

    Scanner SCANNER = new Scanner(System.in);

}
