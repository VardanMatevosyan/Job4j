package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * Interface ICash.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 04.11.2017
 */

public interface ICash<I> {

    /**
     * Add model to the concurrent map if there is not such model as passing to method.
     * @param model is Model.
     */

    void add(I model);

    /**
     * Update the old model in the concurrent map to the new model.
     * @param existModel exist model in the map.
     * @param updateModel new model which is wanna be in the map instead of existModel.
     * @throws OptimisticException throw if model version is not equal between existModel.
     * and the same model in the map.
     * @throws NoSuchElementException throw if you wanna update old model and passing it variable to update method.
     * which not exist in the map.
     */

    void update(I existModel, I updateModel) throws OptimisticException, NoSuchElementException;

    /**
     * delete model from concurrent map.
     * @param model is Model passing to the method to delete.
     * @throws NoSuchElementException throw if model doesn't exist in the map.
     */

    void delete(I model) throws NoSuchElementException;

}
