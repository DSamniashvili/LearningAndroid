package ge.gamp.learningandroid.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

class InMemoryRepository implements Repository{

    // Use either this
    private List<Programmer> programmers;

    @Override
    public void getProgrammers(ResponseHandler<List<Programmer>> handler) {

    }

    @Override
    public void getProgrammer(int id, ResponseHandler<Programmer> handler) {

    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {

    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {

    }

    @Override
    public void addProgrammer(Programmer programmer) {
        // EXAMPLE
        this.programmers.add(programmer);
    }

}
