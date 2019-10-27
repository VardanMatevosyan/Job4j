package ru.matevosyan.action;

import org.reflections.Reflections;
import ru.matevosyan.action.UserAction;
import ru.matevosyan.action.UserActionLoader;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class UserActionLoaderHandler {
private static final String SCAN_PACKAGE = "ru.matevosyan.action";
    static List<UserAction> userActions = new ArrayList<>();
    public static void fillActions() {

//        Reflections reflections = new Reflections(SCAN_PACKAGE);


//        Set<Class<? extends UserAction>> subTypes = reflections.getSubTypesOf(UserAction.class);
//        Set<Class<?>> annotated = null;

        Class[] classes;
        try {
            classes = getClasses(SCAN_PACKAGE);
            for (Class classAction : classes) {
                UserAction action = null;
                Annotation[] annotations = classAction.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof UserActionLoader) {
                        try {
                            action = (UserAction) classAction.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        userActions.add(action);
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
        }
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }


    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
    */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
