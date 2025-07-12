package INTERFACES;

import MODEL.Project;

//-----------------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSABILITY PRINCIPLE------------
public interface IProjectCreator {
    Project createProject(String name);
}
