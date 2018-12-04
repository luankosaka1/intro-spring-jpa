package br.com.devmedia.curso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devmedia.curso.dao.UsuarioDao;
import br.com.devmedia.curso.domain.TipoSexo;
import br.com.devmedia.curso.domain.Usuario;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;

	@ModelAttribute("sexos")
	public TipoSexo[] tipoSexo() {
		return TipoSexo.values();
	}

	@RequestMapping("/todos")
	public ModelAndView todos(ModelMap model) {
		model.addAttribute("usuarios", dao.getTodos());
		return new ModelAndView("usuario/list", model);
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro(@ModelAttribute("usuario") Usuario usuario, ModelMap model) {
		return new ModelAndView("/usuario/add", model);
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/usuario/add";
		}
		dao.salvar(usuario);
		attr.addFlashAttribute("message", "Usuário salvo com sucesso.");
		return "redirect:/usuario/todos";
	}

	@GetMapping("/update/{id}")
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		Usuario usuario = dao.getId(id);
		model.addAttribute("usuario", usuario);
		return new ModelAndView("/usuario/add", model);
	}

	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("usuairo") Usuario usuario, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			return new ModelAndView("/usuario/add");
		}

		dao.editar(usuario);
		attr.addFlashAttribute("message", "Usu�rio atualizado com sucesso");
		return new ModelAndView("redirect:/usuario/todos");
	}

	@PostMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		dao.excluir(id);
		attr.addFlashAttribute("message", "Usu�rio excluido com sucesso");
		return new ModelAndView("redirect:/usuario/todos");
	}

	@GetMapping("/sexo")
	public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo) {
		if (sexo == null) {
			return new ModelAndView("redirect:/usuario/todos");
		}
		
		return new ModelAndView("/usuario/list", "usuarios", dao.getBySexo(sexo));
	}

	@GetMapping("/nome")
	public ModelAndView listarPorNome(@RequestParam(value="nome") String nome) {
		if (nome == null) {
			return new ModelAndView("redirect:/usuario/todos");
		}
		
		return new ModelAndView("/usuario/list","usuarios", dao.getByNome(nome));
	}
}
