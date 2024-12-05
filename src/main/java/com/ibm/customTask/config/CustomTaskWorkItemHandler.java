package com.ibm.customTask.config;

import java.util.Map;
import java.util.HashMap;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
public class CustomTaskWorkItemHandler implements KogitoWorkItemHandler {
    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.out.println("Hello from the custom work item definition.");
        System.out.println("Passed parameters:");
     // Printing task’s parameters, it will also print
     // our value we pass to the task from the process
        for(String parameter : workItem.getParameters().keySet()) {
            System.out.println(parameter + " = " + workItem.getParameters().get(parameter));
        }
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("Result", "Message Returned from Work Item Handler");
     // Don’t forget to finish the work item otherwise the process
     // will be active infinitely and never will pass the flow
     // to the next node.
        manager.completeWorkItem(workItem.getStringId(), results);
   }
    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.err.println("Error happened in the custom work item definition.");
   }
}