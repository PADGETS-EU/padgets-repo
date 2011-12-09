import os, os.path
import shutil
import subprocess

from play.utils import *

COMMANDS = ['javadoc', 'jd']

HELP = {
    'javadoc': 'Generate your application Javadoc'
}

def execute(**kargs):
    command = kargs.get("command")
    app = kargs.get("app")
    args = kargs.get("args")
    play_env = kargs.get("env")

    app.check()
    modules = app.modules()
    if not os.environ.has_key('JAVA_HOME'):
        javadoc_path = "javadoc"
    else:
        javadoc_path = os.path.normpath("%s/bin/javadoc" % os.environ['JAVA_HOME'])

    fileList = []
    jaxbFileList = []
    jaxrsFileList = []
    def add_java_files(app_path, dir, list):
        for root, subFolders, files in os.walk(os.path.join(app_path, dir)):
            for file in files:
                if file.endswith(".java"):
                    list.append(os.path.join(root, file))
    add_java_files(app.path, 'app', fileList)
    for module in modules:
        add_java_files(os.path.normpath(module), 'app', fileList)
    ## jaxb - oneopenapi
    #add_java_files(app.path, 'app\\eu\\padgets\\rest', jaxbFileList)
    ##add_java_files(app.path, 'app\\models', jaxbFileList) 
    ## jaxrs - oneopenapi
    #add_java_files(app.path, 'app\\eu\\padgets\\rest', jaxrsFileList)
    #add_java_files(app.path, 'app\\rest\\conf', jaxrsFileList)
    #add_java_files(app.path, 'app\\models', jaxrsFileList) 
    #add_java_files(app.path, 'app\\utils', jaxrsFileList)
    #add_java_files(app.path, 'app\\component', jaxrsFileList)
    #add_java_files(app.path, 'app\\controllers', jaxrsFileList)
    
    # jaxb - as-padgets
    add_java_files(app.path, 'app\\rest\\dto', jaxbFileList) 
    # jaxrs - as-padgets
    add_java_files(app.path, 'app\\utils', jaxrsFileList)
    add_java_files(app.path, 'app\\rest\\interfaces', jaxrsFileList)
    add_java_files(app.path, 'app\\rest\\impl', jaxrsFileList)
    add_java_files(app.path, 'app\\rest\\conf', jaxrsFileList)
    add_java_files(app.path, 'app\\rest\\dto', jaxrsFileList) 
    add_java_files(app.path, 'app\\components', jaxrsFileList)
    add_java_files(app.path, 'app\\controllers', jaxrsFileList)
    
    outdir = os.path.join(app.path, 'javadoc')
    #outdirjaxb = os.path.join(app.path, 'public\\javadoc-jaxb')
    #outdirjaxrs = os.path.join(app.path, 'public\\javadoc-jaxrs')
    outdirjaxb = 'public\\javadoc-jaxb'
    outdirjaxrs = 'public\\javadoc-jaxrs'
    sout = open(os.path.join(app.log_path(), 'javadoc.log'), 'w')
    serr = open(os.path.join(app.log_path(), 'javadoc.err'), 'w')
    if (os.path.isdir(outdir)):
        shutil.rmtree(outdir)
    if (os.path.isdir(outdirjaxb)):
        shutil.rmtree(outdirjaxb)
    if (os.path.isdir(outdirjaxrs)):
        shutil.rmtree(outdirjaxrs)
    javadoc_cmd = [javadoc_path, '-classpath', app.cp_args(), '-d', outdir] + fileList
    print "Generating Javadoc in " + outdir + "..."
    subprocess.call(javadoc_cmd, env=os.environ, stdout=sout, stderr=serr)
    
    javadoc_cmd = [javadoc_path, '-classpath', app.cp_args(), '-doclet', 'com.lunatech.doclets.jax.jaxb.JAXBDoclet', '-docletpath', 'E:\svn\Padgets_Intern\dev\jax-doclets\jax-doclets-0.9.0.jar', '-d', outdirjaxb, '-charset', 'UTF-8', '-encoding', 'utf-8'] + jaxbFileList
    # print javadoc_cmd
    print "Generating Javadoc JAXB in " + outdirjaxb + "..."
    subprocess.call(javadoc_cmd, env=os.environ, stdout=sout, stderr=serr)
    
    javadoc_cmd = [javadoc_path, '-classpath', app.cp_args(), '-doclet', 'com.lunatech.doclets.jax.jaxrs.JAXRSDoclet', '-docletpath', 'E:\svn\Padgets_Intern\dev\jax-doclets\jax-doclets-0.9.0.jar;E:\svn\Padgets_Intern\dev\jax-doclets\jsr311-api-1.0.jar', '-link', outdirjaxb, '-d', outdirjaxrs, '-charset', 'UTF-8', '-encoding', 'utf-8'] + jaxrsFileList
    # print javadoc_cmd
    print "Generating Javadoc JAX-RS in " + outdirjaxrs + "..."
    subprocess.call(javadoc_cmd, env=os.environ, stdout=sout, stderr=serr)
    
    print "Done! You can open " + os.path.join(outdir, 'overview-tree.html') + " in your browser."