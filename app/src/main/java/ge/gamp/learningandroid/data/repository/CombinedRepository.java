package ge.gamp.learningandroid.data.repository;

import java.util.List;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

public class CombinedRepository implements Repository{
    private static CombinedRepository INSTANCE;
    private FakeRestRepository restRepository;
    private InMemoryRepository inMemoryRepository;

    private CombinedRepository(){
        restRepository = new FakeRestRepository();
        inMemoryRepository = new InMemoryRepository();
    };

    public static CombinedRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CombinedRepository();
        }

        return INSTANCE;
    }


    @Override
    public void getProgrammers(ResponseHandler<List<Programmer>> handler) {
        // ToDO: Change this to work with both repositories
        inMemoryRepository.getProgrammers(inMemoryProgrammer -> {
            if(inMemoryProgrammer == null){
                restRepository.getProgrammers(handler);
            }else{
                handler.handleResponse(inMemoryProgrammer);
            }
        });
    }

    @Override
    public void getProgrammer(int id, ResponseHandler<Programmer> handler) {
        inMemoryRepository.getProgrammer(id, response -> {
            if(response == null){
                restRepository.getProgrammer(id, handler);
            }else{
                handler.handleResponse(response);
            }
        });
    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {
        // ToDO: Change this to work with both repositories
        restRepository.deleteProgrammer(id, (result) -> {
            inMemoryRepository.deleteProgrammer(id, handler);
        });
    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {
        // ToDO: Change this to work with both repositories
        restRepository.createProgrammer(programmer, result -> {
            inMemoryRepository.addProgrammer(programmer);
        });
    }

    @Override
    public void setProgrammers(List<Programmer> programmers) {
        inMemoryRepository.setProgrammers(programmers);
    }

    @Override
    public void addProgrammer(Programmer programmer) {
        inMemoryRepository.addProgrammer(programmer);
    }
}
