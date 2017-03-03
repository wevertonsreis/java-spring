package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "conta/formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "conta/formulario";
		}
		
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.adiciona(conta);
		
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public String listar(Model mv) {
		
		ContaDAO contaDAO = new ContaDAO();
		List<Conta> listaDeConta = contaDAO.lista();
		
		mv.addAttribute("listaDeConta", listaDeConta);
		
		return "conta/lista";
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.remove(conta);
		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/pagaConta")
	public void pagar(Long id, HttpServletResponse response) {
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.paga(id);
		
		response.setStatus(200);
	}
	
	@RequestMapping("/mostraConta")
	public String mostrar(Long id, Model model) {
		ContaDAO contaDAO = new ContaDAO();
		
		model.addAttribute("conta", contaDAO.buscaPorId(id));
		
		return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String alterar(Conta conta) {
		ContaDAO contaDAO = new ContaDAO();
		
		contaDAO.altera(conta);
		
		return "redirect:listaContas";
	}
	
}
