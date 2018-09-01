# http://yui.github.io/yuicompressor/
#
# java -jar yuicompressor-x.y.z.jar
# Usage: java -jar yuicompressor-x.y.z.jar [options] [input file]
#
#   Global Options
#     -h, --help                Displays this information
#     --type <js|css>           Specifies the type of the input file
#     --charset <charset>       Read the input file using <charset>
#     --line-break <column>     Insert a line break after the specified column number
#     -v, --verbose             Display informational messages and warnings
#     -o <file>                 Place the output into <file> or a file pattern.
#                               Defaults to stdout.
#
#   JavaScript Options
#     --nomunge                 Minify only, do not obfuscate
#     --preserve-semi           Preserve all semicolons
#     --disable-optimizations   Disable all micro optimizations
#
# GLOBAL OPTIONS
#
#   -h, --help
#       Prints help on how to use the YUI Compressor
#
#   --line-break
#       Some source control tools don't like files containing lines longer than,
#       say 8000 characters. The linebreak option is used in that case to split
#       long lines after a specific column. It can also be used to make the code
#       more readable, easier to debug (especially with the MS Script Debugger)
#       Specify 0 to get a line break after each semi-colon in JavaScript, and
#       after each rule in CSS.
#
#   --type js|css
#       The type of compressor (JavaScript or CSS) is chosen based on the
#       extension of the input file name (.js or .css) This option is required
#       if no input file has been specified. Otherwise, this option is only
#       required if the input file extension is neither 'js' nor 'css'.
#
#   --charset character-set
#       If a supported character set is specified, the YUI Compressor will use it
#       to read the input file. Otherwise, it will assume that the platform's
#       default character set is being used. The output file is encoded using
#       the same character set.
#
#   -o outfile
#
#       Place output in file outfile. If not specified, the YUI Compressor will
#       default to the standard output, which you can redirect to a file.
#       Supports a filter syntax for expressing the output pattern when there are
#       multiple input files.  ex:
#           java -jar yuicompressor.jar -o '.css$:-min.css' *.css
#       ... will minify all .css files and save them as -min.css
#
#   -v, --verbose
#       Display informational messages and warnings.
#
# JAVASCRIPT ONLY OPTIONS
#
#   --nomunge
#       Minify only. Do not obfuscate local symbols.
#
#   --preserve-semi
#       Preserve unnecessary semicolons (such as right before a '}') This option
#       is useful when compressed code has to be run through JSLint (which is the
#       case of YUI for example)
#
#   --disable-optimizations
#       Disable all the built-in micro optimizations.
#
# -------------- start package javascript --------------

## cat a > b - 将a文件内容合并到b文件中
## cat a >> b - 将a文件内容追加到b文件中

cat ../../blog-web/src/main/resources/static/js/zhyd.core.js > zhyd.all.js
cat ../../blog-web/src/main/resources/static/js/zhyd.js >> zhyd.all.js
cat ../../blog-web/src/main/resources/static/js/zhyd.comment.js >> zhyd.all.js
java -jar yuicompressor-2.4.8.jar zhyd.all.js -o ../../blog-web/src/main/resources/static/js/zhyd.min.js --charset utf-8 --type js
rm -f zhyd.all.js


# -------------- start package css --------------
cat ../../blog-web/src/main/resources/static/css/zhyd.core.css > zhyd.all.css
cat ../../blog-web/src/main/resources/static/css/zhyd.comment.css >> zhyd.all.css
java -jar yuicompressor-2.4.8.jar zhyd.all.css -o ../../blog-web/src/main/resources/static/css/zhyd.min.css --charset utf-8 --type css

rm -f zhyd.all.css