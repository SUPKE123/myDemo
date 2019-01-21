package com.sinotrans.test;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.sun.source.util.Trees;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;


/**
 * 注解处理器
 * @author zhangbd
 * @dateTime 2019年1月21日下午4:27:30
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.sinotrans.test.*")
public class MyAnnotaionProcessor extends AbstractProcessor{
	
	private Messager messager;
	
	private JavacTrees trees;
	
	private TreeMaker treeMaker;
	
	private Names names;
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.messager = processingEnv.getMessager();
		this.trees = JavacTrees.instance(processingEnv);
		Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
		this.treeMaker = TreeMaker.instance(context);
		this.names = Names.instance(context);
	}

	@Override 
	public boolean process(Set<? extends TypeElement> annotations,RoundEnvironment roundEnv){
		roundEnv.getElementsAnnotatedWith(MyAnnotation.class).stream().map(element-> trees.getTree(element)).forEach(t-> t.accept(new TreeTranslator(){

			@Override
			public void visitClassDef(JCClassDecl arg0) {
				super.visitClassDef(arg0);
			}
			
		}));
		return true;
	}

}
