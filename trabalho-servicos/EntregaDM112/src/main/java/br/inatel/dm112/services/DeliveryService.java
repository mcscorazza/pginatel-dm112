package br.inatel.dm112.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.DeliveryStatus;
import br.inatel.dm112.model.DeliveryStatus.DELIVERY_STATUS;

@Service
public class DeliveryService {

	@Autowired
	private OrderClient clientOrder;

	@Autowired
	private EmailClient clientEmail;

	/**
	 * Lógica de geração de pendência de entrega (1) consulta o pedido pelo número
	 * (2) atualiza o status do pedido (3) envia email (4) retorna sucesso
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public DeliveryStatus startDeliveryOfOrder(String cpf, int orderNumber) {

		Order order = getOrder(cpf, orderNumber); // (1) consulta o pedido pelo número

		if (order.getStatus() != Order.STATUS.CONFIRMED.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.WRONG_ORDER_STATUS);
		}

		try {
			clientOrder.startOrderDelivery(orderNumber); // (2) atualiza o status do pedido
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: start delivery: " + e.getMessage();
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.ORDER_ERROR);
		}

		try {
			clientEmail.callSendMailService(orderNumber, null); // (4) envia email
		} catch (Exception e) {
			String msg = "Erro no serviço de email";
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.EMAIL_ERROR);
		}
		System.out.println("Sucesso ao inicializar a entrega: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new DeliveryStatus(DELIVERY_STATUS.OK.ordinal(), cpf, orderNumber); // (5) retorna sucesso
	}

	/**
	 * Lógica de confirmação de entrega (1) consulta o pedido pelo número (2)
	 * atualiza o status do pedido confirmando a entrega (3) responde Ok
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public DeliveryStatus confirmDeliveryOfOrder(String cpf, int orderNumber) {

		Order order = getOrder(cpf, orderNumber); // (1) consulta o pedido pelo número

		if (order.getStatus() != Order.STATUS.SHIPPED.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.WRONG_ORDER_STATUS);
		}

		try {
			clientOrder.confirmOrderDelivery(orderNumber); // (2) confirma a entrega (e atualiza o status)
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: confirm delivery";
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar a entrega: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new DeliveryStatus(DELIVERY_STATUS.OK.ordinal(), cpf, orderNumber); // (3) responde Ok
	}

	private Order getOrder(String cpf, int orderNumber) {
		if (cpf == null || orderNumber < 0) {
			throw DeliveryStatus.createErrorStatus("CPF e pedido são obrigatórios", cpf, orderNumber,
					DELIVERY_STATUS.NULL_VALUES);
		}
		Order order;
		try {
			order = clientOrder.retrieveOrder(orderNumber); // (1) consulta o pedido pelo número
		} catch (Exception e) {
			String msg = "Pedido " + orderNumber + " não encontrado.";
			throw DeliveryStatus.createErrorStatus(msg, cpf, orderNumber, DELIVERY_STATUS.ORDER_NOT_FOUND);
		}
		return order;
	}

}