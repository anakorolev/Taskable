package com.taskable.Controller;

import com.taskable.Views.projectSidePanelView;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by akorolev on 11/6/17.
 */

public class projectSidePanelController extends MouseAdapter {

    public projectSidePanelController(projectSidePanelView v) {
        view = v;
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    // Member Variables
    projectSidePanelView view;

}