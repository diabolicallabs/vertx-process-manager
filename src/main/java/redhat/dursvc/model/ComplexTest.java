package redhat.dursvc.model;

import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.internal.runtime.helper.BatchExecutionHelper;

import java.util.ArrayList;
import java.util.List;

public class ComplexTest {

  static class Patient {

    String fName;
    Drug perscription;
    List<Drug> drugList = new ArrayList<>();

    public Drug getPerscription() {
      return perscription;
    }

    public void setPerscription(Drug perscription) {
      this.perscription = perscription;
    }

    public List<Drug> getDrugList() {
      return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
      this.drugList = drugList;
    }


    public String getfName() {
      return fName;
    }

    public void setfName(String fName) {
      this.fName = fName;
    }
  }

  static class Drug {
    public String getDrugName() {
      return drugName;
    }

    public void setDrugName(String drugName) {
      this.drugName = drugName;
    }

    String drugName;
  }

  public static void main(String[] args) {

    Drug seldane = new Drug();
    seldane.setDrugName("Seldane");
    Drug biaxin = new Drug();
    biaxin.setDrugName("Biaxin");

    Patient patient = new Patient();

    patient.setfName("Goku");
    patient.setPerscription(seldane);
    patient.getDrugList().add(biaxin);

    List<GenericCommand<?>> commands = new ArrayList<>();

    commands.add(new InsertObjectCommand(patient));
    commands.add(new FireAllRulesCommand());

    BatchExecutionCommand command = (BatchExecutionCommand) new BatchExecutionCommandImpl(commands);
    String json = BatchExecutionHelper.newJSonMarshaller().toXML(command);

    System.out.println(json);
  }
}
