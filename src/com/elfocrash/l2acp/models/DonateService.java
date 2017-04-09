package com.elfocrash.l2acp.models;

public class DonateService {
	public int ServiceId;
	public String ServiceName;
	public int Price;
	
	public DonateService(int serviceId,String serviceName,int price){
		ServiceId = serviceId;
		ServiceName = serviceName;
		Price = price;
	}
}
