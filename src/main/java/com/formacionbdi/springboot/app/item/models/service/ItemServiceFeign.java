package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return this.clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item finfById(Long id, Integer cantidad) {
		return new Item(this.clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return this.clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return this.clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		this.clienteFeign.eliminar(id);
	}

}
