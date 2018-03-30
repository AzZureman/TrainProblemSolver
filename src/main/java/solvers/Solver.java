package solvers;

import java.util.*;

//это же какой-то SolverContainer, SolverDispatcher, почему просто Solver?
//+ я вы выделил в отдельный пакет от просто кучи солверов
//+ feature request: что б он умел сам солверы подтягивать, достатончо было бы просто закоммитить
// -- тут тебе либо городить велосипед, либо взять какую-нибудь DI-либу, DI-фреймворк - на спринге такое делается изи, но он огромный
public final class Solver {

    private static final List<Class<? extends ISolver>> solverList = List.of(
            UnSolver.class,
            AzZuSolver.class
    );

    public static List<String> getSolversNames(){
        List<String> solversNames = new ArrayList<>();
        for (Class<? extends ISolver> solverClass : solverList) {
            solversNames.add(solverClass.getSimpleName());
        }
        return solversNames;
    }

    public static ISolver getSolverByName(String SolverName) {

        try {
            return (ISolver)(Class.forName("solvers." + SolverName).getConstructor().newInstance());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
