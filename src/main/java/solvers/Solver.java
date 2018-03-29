package solvers;

import java.util.*;

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
