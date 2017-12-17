import java.util.LinkedList;

public class Experiments
{
	/**************************************************************************
	Particular tests
	*****************************************************************************/

	public static PlacementConfiguration TC1()
	{
		CloudInfrastructure CI = new CloudInfrastructure();
		CI.add(new Host(3,3));		
		CI.add(new Host(3,2));		

		VMConfiguration VC = new VMConfiguration();
		VC.add(new VM(3,2));
		VC.add(new VM(2,3));
		VC.add(new VM(2,2));
		VC.add(new VM(1,1));

		return new PlacementConfiguration(CI, VC);
	}
	
	public static PlacementConfiguration TC2()
	{
		CloudInfrastructure CI = new CloudInfrastructure();
		CI.add(new Host(3,2));		
		CI.add(new Host(3,3));		
		CI.add(new Host(3,2));		

		VMConfiguration VC = new VMConfiguration();
		VC.add(new VM(3,2));
		VC.add(new VM(2,3));
		VC.add(new VM(2,2));
		VC.add(new VM(1,1));

		return new PlacementConfiguration(CI, VC);
	}

	public static PlacementConfiguration TC3()
	{
		CloudInfrastructure CI = new CloudInfrastructure();
		CI.add(new Host(64,96));		
		CI.add(new Host(96,64));		
		CI.add(new Host(32,32));		

		VMConfiguration VC = new VMConfiguration();
		VC.add(new VM(3,2));
		VC.add(new VM(2,3));
		VC.add(new VM(2,2));
		VC.add(new VM(1,1));

		return  new PlacementConfiguration(CI, VC);
	}

	public static PlacementConfiguration TC4()
	{
		CloudInfrastructure CI = new CloudInfrastructure();
		CI.add(new Host(64,96));		
		CI.add(new Host(96,64));		
		CI.add(new Host(32,32));		
		CI.add(new Host(64,96));		
		CI.add(new Host(96,64));		
		CI.add(new Host(32,32));		
		CI.add(new Host(64,96));		
		CI.add(new Host(96,64));		
		CI.add(new Host(32,32));		

		VMConfiguration VC = new VMConfiguration();
		VC.add(new VM(3,2));
		VC.add(new VM(2,3));
		VC.add(new VM(2,2));
		VC.add(new VM(1,1));

		return  new PlacementConfiguration(CI, VC);
	
	}

	public static LinkedList<VMPlacementTestCase> createTestCases()
	{
		LinkedList<VMPlacementTestCase> testcases = new LinkedList();
		testcases.add(VMPlacementTester.getResourceUtilizationTC(TC1()));		
		testcases.add(VMPlacementTester.getResourceUtilizationTC(TC2()));		
		testcases.add(VMPlacementTester.getResourceUtilizationTC(TC3()));		
		testcases.add(VMPlacementTester.getResourceUtilizationTC(TC4()));		
		return testcases;
	}

	public static PlacementConfiguration ptc1()
	{
		CloudInfrastructure CI = new CloudInfrastructure();
		CI.add(new Host(4,3));		
		CI.add(new Host(4,3));		

		VMConfiguration VC = new VMConfiguration();
		VC.add(new VM(4,1));
		VC.add(new VM(2,2));
		VC.add(new VM(1,1));

		PowerConsumptionProfile CP = new PowerConsumptionProfile();
		CP.add(new PowerConsumption(10,15,5));
		CP.add(new PowerConsumption(10,5,15));

		return new PlacementConfiguration(CI, VC, CP);
	}


	public static LinkedList<VMPlacementTestCase> PCTestCases()
	{
		LinkedList<VMPlacementTestCase> testcases = new LinkedList();
		testcases.add(VMPlacementTester.getPowerConsumptionTC(ptc1()));
		return testcases;
	}

	public static void main(String args[])
	{
		//LinkedList<VMPlacementTestCase> testcases = createTestCases();

		LinkedList<VMPlacementTestCase> testcases = PCTestCases();
		VMPlacementTester tester = new VMPlacementTester(false, 100, testcases);
		AvailableRandomPlacement arp = new AvailableRandomPlacement();	
		FirstFitPlacement ffp = new FirstFitPlacement();
		//tester.test(arp, VMPlacementTester::occupation, "Occupation");
		//System.out.println();
		//tester.test(ffp, VMPlacementTester::occupation, "Occupation");
		//System.out.println("\n\n");
		tester.test(arp, VMPlacementTester::powerConsumption, "Power Consumption");
		System.out.println();
		tester.test(ffp, VMPlacementTester::powerConsumption, "Power Consumption");

	}

}
