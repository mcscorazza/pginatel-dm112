package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.DeliveryStatus;

public interface Delivery {

	DeliveryStatus startDeliveryOfOrder(String cpf, int orderNumber);

	DeliveryStatus confirmDeliveryOfOrder(String cpf, int orderNumber);

}