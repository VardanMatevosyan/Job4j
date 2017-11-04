package ru.matevosyan;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cash class for no blocking cash algorithm.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 04.11.2017
 */

public class Cash<I extends Model> implements ICash<I> {
    private ConcurrentHashMap<Integer, Model> cashMap;

    /**
     * Constructor for Cash class.
     */

    public Cash() {
        this.cashMap = new ConcurrentHashMap<>();
    }

    /**
     * Getter for cashMap.
     * @return cashMap variable.
     */

    public ConcurrentHashMap<Integer, Model> getCashMap() {
        return cashMap;
    }

    /**
     * Add model to the concurrent map if there is not such model as passing to method.
     * @param model is Model.
     */

    @Override
    public void add(Model model) {
        this.cashMap.putIfAbsent(model.getId(), model);
    }

    /**
     * Update the old model in the concurrent map to the new model.
     * @param existModel exist model in the map.
     * @param updateModel new model which is wanna be in the map instead of existModel.
     * @throws OptimisticException throw if model version is not equal between existModel.
     * and the same model in the map.
     * @throws NoSuchElementException throw if you wanna update old model and passing it variable to update method.
     * which not exist in the map.
     */

    @Override
    public void update(Model existModel, Model updateModel) throws OptimisticException, NoSuchElementException {
        Model modelInCash;
        if (this.cashMap.contains(existModel)) {
            modelInCash = this.cashMap.get(existModel.getId());
        } else {
            throw new NoSuchElementException("Model that you wanna change does't exist");
        }
        if (existModel.getVersion() == modelInCash.getVersion()) {
            cashMap.computeIfPresent(existModel.getId(), (key, value) -> value = updateModel);
            updateModel.version++;
        } else {
            throw new OptimisticException("Version of model to update is not the same");
        }
    }

    /**
     * delete model from concurrent map.
     * @param model is Model passing to the method to delete.
     * @throws NoSuchElementException throw if model doesn't exist in the map.
     */

    @Override
    public void delete(Model model) throws NoSuchElementException {
        Model modelInCash = this.cashMap.get(model.getId());
        if (model.getVersion() == modelInCash.getVersion()) {
            this.cashMap.remove(model.getId());
        } else {
            throw new NoSuchElementException("No such element");
        }
    }
}
