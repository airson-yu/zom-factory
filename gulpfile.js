var gulp = require("gulp");
var babel = require("gulp-babel");
var uglify = require('gulp-uglify');

gulp.task('babel', function () {
    return gulp.src("src/main/resources/static/js/**/*.js")
    //.pipe(uglify())
        .pipe(babel())
        .pipe(gulp.dest("src/main/resources/static/dist/js"));
});

var watcher = gulp.watch('src/main/resources/static/js/**/*.js', ['babel']);
watcher.on('change', function (event) {
    console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
});


gulp.task("default", ['babel']);
