package ge.gamp.learningandroid.data.repository;

import java.util.List;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

public interface Repository {
    void getProgrammers(ResponseHandler<List<Programmer>> handler);
    void getProgrammer(int id, ResponseHandler<Programmer> handler);
    void deleteProgrammer(int id, ResponseHandler<Boolean> handler);
    void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler);
    void setProgrammers(List<Programmer> programmers);
    void addProgrammer(Programmer programmer);
//    void updateProgrammer(Programmer programmer, ResponseHandler<Programmer> handler);
}
