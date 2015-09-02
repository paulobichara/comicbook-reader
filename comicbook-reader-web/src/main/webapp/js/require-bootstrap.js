requirejs.config({
	
    baseUrl: 'js',
    
    paths: {
        'jquery' : 'lib/jquery/dist/jquery.min',
        'angular' : 'lib/angular/angular.min'
    },
    
    shim: {
        'angular' : { 'deps' : ['jquery'], 'exports' : 'angular' }
    },
    
    deps: ['angular-bootstrap']
});