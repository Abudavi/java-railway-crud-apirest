package com.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepositories;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositories productoRepositories;

    @GetMapping()
    public List<Producto> getAllProductos() {

        return productoRepositories.findAll();
    }

    @PostMapping()
    public Producto createProducto(@RequestBody Producto producto) {

        return productoRepositories.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PutMapping("/{id}")
    public Producto updateProductoById(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        Producto producto = productoRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepositories.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Producto producto = productoRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
        productoRepositories.delete(producto);
        return "El producto con el ID: " + id + " se ha borrado exitosamente";
    }

}
