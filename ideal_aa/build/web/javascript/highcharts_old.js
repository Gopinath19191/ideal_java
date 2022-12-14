/*
 Name:    Highcharts
 Version: 1.2.4 (2010-03-10)
 Author:  Vevstein Web
 Support: www.highcharts.com/support
 License: www.highcharts.com/license
*/
(function(){
    function ic(a){
        if(!a||a.constructor!=Array)a=[a];return a
        }function Za(a){
        return a!==ma&&a!==null
        }function fa(){
        var a=arguments,b,c;for(b=0;b<a.length;b++){
            c=a[b];if(Za(c))return c
                }
        }function Ab(a,b,c){
        var d,e="",f=c?"print":"",g=function(i){
            return T("style",{
                type:"text/css",
                media:i?"print":""
                },null,sa.getElementsByTagName("HEAD")[0])
            };jc||(jc=g());for(d in b)e+=Ib(d)+":"+b[d]+";";if(Na){
            b=sa.styleSheets;c&&g(true);for(c=b.length-1;c>=0&&b[c].media!=f;)c--;f=b[c];f.addRule(a,e)
            }else jc.appendChild(sa.createTextNode(a+
            " {"+e+"}\n"))
        }function J(a,b){
        a||(a={});for(var c in b)a[c]=b[c];
        //alert(a);
        return a
        }function Uc(a){
        Da=Y(Da,a);Ac();return Da
        }function ya(a){
        Jb||(Jb=T(Ra));a&&Jb.appendChild(a);Jb.innerHTML=""
        }function hb(a,b){
        var c=function(){};c.prototype=new a;J(c.prototype,b);return c
        }function Bb(a,b){
        if(typeof a=="string")return a;else if(a.linearGradient){
            var c=b.createLinearGradient.apply(b,a.linearGradient);p(a.stops,function(d){
                c.addColorStop(d[0],d[1])
                });return c
            }
        }function T(a,b,c,d,e){
        a=sa.createElement(a);b&&J(a,
            b);e&&oa(a,{
            padding:0,
            border:"none",
            margin:0
        });c&&oa(a,c);d&&d.appendChild(a);return a
        }function oa(a,b){
        if(Na)if(b.opacity!==ma)b.filter="alpha(opacity="+b.opacity*100+")";J(a.style,b)
        }function Vc(a,b,c,d){
        var e=Da.lang;a=a;var f=isNaN(b=za(b))?2:b;b=c===ma?e.decimalPoint:c;d=d===ma?e.thousandsSep:d;e=a<0?"-":"";c=parseInt(a=za(+a||0).toFixed(f))+"";var g=(g=c.length)>3?g%3:0;return e+(g?c.substr(0,g)+d:"")+c.substr(g).replace(/(\d{3})(?=\d)/g,"$1"+d)+(f?b+za(a-c).toFixed(f).slice(2):"")
        }function Bc(a,
        b,c){
        function d(y){
            return y.toString().replace(/^([0-9])$/,"0$1")
            }if(!Za(b))return"Invalid date";b=new Date(b*Oa);var e=b[kc](),f=b[lc](),g=b[Cb](),i=b[Kb](),j=b[Lb](),k=Da.lang,q=k.weekdays;k=k.months;b={
            a:q[f].substr(0,3),
            A:q[f],
            d:d(g),
            e:g,
            b:k[i].substr(0,3),
            B:k[i],
            m:d(i+1),
            y:j.toString().substr(2,2),
            Y:j,
            H:d(e),
            I:d(e%12||12),
            l:e%12||12,
            M:d(b[mc]()),
            p:e<12?"AM":"PM",
            P:e<12?"am":"pm",
            S:d(b.getSeconds())
            };for(var w in b)a=a.replace("%"+w,b[w]);return c?a.substr(0,1).toUpperCase()+a.substr(1):a
        }function Ac(){
        var a=
        Da.global.useUTC;Mb=a?Date.UTC:function(b,c,d,e,f,g){
            return(new Date(b,c,fa(d,1),fa(e,0),fa(f,0),fa(g,0))).getTime()
            };mc=a?"getUTCMinutes":"getMinutes";kc=a?"getUTCHours":"getHours";lc=a?"getUTCDay":"getDay";Cb=a?"getUTCDate":"getDate";Kb=a?"getUTCMonth":"getMonth";Lb=a?"getUTCFullYear":"getFullYear";Cc=a?"setUTCMinutes":"setMinutes";Dc=a?"setUTCHours":"setHours";nc=a?"setUTCDate":"setDate";Ec=a?"setUTCMonth":"setMonth";Fc=a?"setUTCFullYear":"setFullYear"
        }function Nb(a){
        for(var b={
            x:a.offsetLeft,
            y:a.offsetTop
            };a.offsetParent;){
            a=a.offsetParent;b.x+=a.offsetLeft;b.y+=a.offsetTop;if(a!=sa.body&&a!=sa.documentElement){
                b.x-=a.scrollLeft;b.y-=a.scrollTop
                }
            }return b
        }function Wc(a){
        function b(l,h){
            var t;h=fa(h,true);na(u,"addSeries",{
                options:l
            },function(){
                t=d(l);t.isDirty=true;u.isDirty=true;h&&u.redraw()
                });return t
            }function c(){
            var l=u.isDirty;p(ta,function(h){
                if(h.isDirty){
                    h.cleanData();h.getSegments();if(h.options.legendType=="point")l=true
                        }
                });wb=null;if(Ob){
                p(Ja,function(h){
                    h.setScale()
                    });j();
                p(Ja,function(h){
                    h.isDirty&&h.redraw()
                    })
                }p(ta,function(h){
                h.isDirty&&h.visible&&h.redraw()
                });if(l){
                if(Pb.renderHTML){
                    Pb.renderHTML(true);Pb.drawGraphics(true)
                    }u.isDirty=false
                }qb&&qb.resetTracker&&qb.resetTracker();na(u,"redraw")
            }function d(l){
            var h=l.type||L.defaultSeriesType,t=Xc[h],r=u.hasRendered;if(r)if(Sa&&h=="column")t=Gc;else if(!Sa&&h=="bar")t=Qb;h=new t;h.init(u,l);if(!r&&h.inverted)Sa=true;ta.push(h);return h
            }function e(){
            var l=a.loading;if(!rb){
                rb=T(Ra,{
                    className:"highcharts-loading"
                },
                J(l.style,{
                    left:W+C,
                    top:F+C,
                    width:pa+C,
                    height:ka+C,
                    zIndex:10,
                    display:"none"
                }),la);T("span",{
                    innerHTML:a.lang.loading
                    },l.labelStyle,rb)
                }oa(rb,{
                display:""
            });xb(rb,{
                opacity:l.style.opacity
                },{
                duration:l.showDuration
                })
            }function f(){
            xb(rb,{
                opacity:0
            },{
                duration:a.loading.hideDuration,
                complete:function(){
                    oa(rb,{
                        display:"none"
                    })
                    }
                })
            }function g(l){
            var h,t,r;for(h=0;h<Ja.length;h++)if(Ja[h].options.id==l)return Ja[h];for(h=0;h<ta.length;h++)if(ta[h].options.id==l)return ta[h];for(h=0;h<ta.length;h++){
                r=ta[h].data;
                for(t=0;t<r.length;t++)if(r[t].id==l)return r[t]
                    }return null
            }function i(){
            var l=a.xAxis||{},h=a.yAxis||{},t;l=ic(l);p(l,function(r,N){
                r.index=N;r.isX=true
                });h=ic(h);p(h,function(r,N){
                r.index=N
                });Ja=l.concat(h);u.xAxis=[];u.yAxis=[];Ja=mb(Ja,function(r){
                t=new Pa(u,r);u[t.isXAxis?"xAxis":"yAxis"].push(t);return t
                });j()
            }function j(){
            L.alignTicks!==false&&p(Ja,function(l){
                l.adjustTickAmount()
                })
            }function k(){
            var l=[];p(ta,function(h){
                l=l.concat(Rb(h.data,function(t){
                    return t.selected
                    }))
                });return l
            }function q(){
            return Rb(ta,
                function(l){
                    return l.selected
                    })
            }function w(l){
            var h=Da.lang;u.toolbar.add("zoom",h.resetZoom,h.resetZoomTitle,function(){
                na(u,"selection",{
                    resetSelection:true
                },w);u.toolbar.remove("zoom")
                });!l||l.resetSelection?p(Ja,function(t){
                t.setExtremes(null,null,false)
                }):p(l.xAxis.concat(l.yAxis),function(t){
                var r=t.axis;if(u.tracker[r.isXAxis?"zoomX":"zoomY"])r.setExtremes(t.min,t.max,false)
                    });c()
            }function y(){
            var l=a.title,h=a.subtitle;if(!u.titleLayer){
                var t=new qa("title-layer",la,null,{
                    zIndex:2
                });l&&l.text&&
                T("h2",{
                    className:"highcharts-title",
                    innerHTML:l.text
                    },l.style,t.div);h&&h.text&&T("h3",{
                    className:"highcharts-subtitle",
                    innerHTML:h.text
                    },h.style,t.div);u.titleLayer=t
                }
            }function H(){
            var l=true;for(var h in u.resources)u.resources[h]||(l=false);l&&G()
            }function G(){
            i();p(ta,function(l){
                l.translate();l.setTooltipPoints();a.tooltip.enabled&&l.createArea()
                });u.render=R;setTimeout(function(){
                R();na(u,"load")
                },0)
            }function D(){
            Ta=L.renderTo;oc="highcharts-"+pc++;if(typeof Ta=="string")Ta=sa.getElementById(Ta);
            Ta.innerHTML="";if(!Ta.offsetWidth){
                nb=Ta.cloneNode(0);oa(nb,{
                    position:ua,
                    top:"-9999px",
                    display:""
                });sa.body.appendChild(nb)
                }var l=(nb||Ta).offsetHeight;ib=L.width||(nb||Ta).offsetWidth||600;Ea=L.height||(l>F+Fa?l:0)||400;la=T(Ra,{
                className:"highcharts-container"+(L.className?" "+L.className:""),
                id:oc
            },J({
                position:Sb,
                overflow:$a,
                width:ib+C,
                height:Ea+C,
                textAlign:"left"
            },L.style),nb||Ta)
            }function R(){
            var l,h=a.labels,t=a.credits;l=2*(L.borderWidth||0)+(L.shadow?8:0);Hc.drawRect(l/2,l/2,ib-l,Ea-l,L.borderColor,
                L.borderWidth,L.borderRadius,L.backgroundColor,L.shadow);Hc.drawRect(W,F,pa,ka,null,null,null,L.plotBackgroundColor,null,Tb);(new qa("plot-border",la,null,{
                zIndex:4
            })).drawRect(W,F,pa,ka,L.plotBorderColor,L.plotBorderWidth,null,null,L.plotShadow);Na&&Ab(".highcharts-image-map",{
                display:"none"
            },"print");Ob&&p(Ja,function(r){
                r.render()
                });y();h.items&&p(h.items,function(){
                var r=J({
                    className:"highcharts-label"
                },this.attributes);qc.drawHtml(this.html,r,J(h.style,this.style))
                });p(ta,function(r){
                r.render()
                });
            Pb=u.legend=new ab(u);if(!u.toolbar)u.toolbar=Ga(u);if(t.enabled&&!u.credits)u.credits=T("a",{
                className:"highcharts-credits",
                href:t.href,
                innerHTML:t.text,
                target:t.target
                },J(t.style,{
                zIndex:8
            }),la);u.hasRendered=true;if(nb){
                Ta.appendChild(la);ya(nb);bb=Nb(la)
                }
            }function Ua(){
            function l(h){
                var t=h.attributes,r,N;if(t){
                    r=t.length;for(r=r-1;r>=0;r-=1){
                        N=t[r].name;try{
                            if(typeof h[N]!="object")h[N]=null
                                }catch(ca){}
                        }
                    }if(t=h.childNodes){
                    r=t.length;for(r=r-1;r>=0;r--){
                        t=h.childNodes[r];l(t);t.childNodes.length||
                        ya(t)
                        }
                    }
                }p(ta,function(h){
                h.destroy()
                });ta=[];l(la)
            }function Pa(l,h){
            function t(){
                h=Y(va?Ub:rc,ga?Aa?Yc:Ic:Aa?Zc:$c,h)
                }function r(){
                var m=[],n;Ka=Qa=null;Vb=[];p(ta,function(v){
                    n=false;p(["xAxis","yAxis"],function(K){
                        if((K=="xAxis"&&va||K=="yAxis"&&!va)&&(v.options[K]==h.index||v.options[K]===ma&&h.index==0)){
                            v[K]=cb;Vb.push(v);n=true
                            }
                        });if(!v.visible&&L.ignoreHiddenSeries)n=false;if(n){
                        var x;if(!va){
                            x=v.options.stacking;Wb=x=="percent";if(x){
                                var z=m[v.type]||[];m[v.type]=z
                                }if(Wb){
                                Ka=0;Qa=99
                                }
                            }if(v.isCartesian){
                            Ob=
                            true;p(v.data,function(K){
                                var E=K.x,ja=K.y;if(Ka===null)Ka=Qa=K[Xb];if(va)if(E>Qa)Qa=E;else{
                                    if(E<Ka)Ka=E
                                        }else if(Za(ja)){
                                    if(x)z[E]=z[E]?z[E]+ja:ja;K=z?z[E]:ja;if(!Wb)if(K>Qa)Qa=K;else if(K<Ka)Ka=K;if(x)db[v.type][E]={
                                        total:K,
                                        cum:K
                                    }
                                    }
                                });if(!va&&/(area|column|bar)/.test(v.type))if(Ka>=0){
                                Ka=0;Jc=true
                                }else if(Qa<0){
                                Qa=0;Kc=true
                                }
                            }
                        }
                    })
                }function N(m,n,v){
                var x=1,z=0;if(v){
                    x*=-1;z=Db
                    }if(sb){
                    x*=-1;z-=x*Db
                    }if(n){
                    if(sb)m=Db-m;m=m/tb+U
                    }else m=x*(m-U)*tb+z;return m
                }function ca(m,n,v){
                if(v){
                    var x,z,K;x=N(m);var E;
                    m=z=x+Yb;x=K=Ea-x-Yb;if(ga){
                        x=F;K=Ea-Fa;if(m<W||m>W+pa)E=true
                            }else{
                        m=W;z=ib-Ba;if(x<F||x>F+ka)E=true
                            }E||Lc.drawLine(m,x,z,K,n,v)
                    }
                }function wa(m,n,v){
                m=Zb(m,U);n=Math.min(n,ha);var x=(n-m)*tb;ca(m+(n-m)/2,v,x)
                }function A(m,n,v,x,z,K,E){
                var ja,Z,eb,O=h.labels;if(n=="inside")z=-z;if(Aa)z=-z;n=Z=N(m+ub)+Yb;ja=eb=Ea-N(m+ub)-Yb;if(ga){
                    ja=Ea-Fa-(Aa?ka:0)+ob;eb=ja+z
                    }else{
                    n=W+(Aa?pa:0)+ob;Z=n-z
                    }x&&yb.drawLine(n,ja,Z,eb,v,x);if(K&&O.enabled)if((m=$b.call({
                    index:E,
                    isFirst:m==da[0],
                    isLast:m==da[da.length-1],
                    value:Ha&&Ha[m]?Ha[m]:m
                    }))||m===0)yb.addText(m,n+O.x-(ub&&ga?ub*tb*(sb?-1:1):0),ja+O.y-(ub&&!ga?ub*tb*(sb?1:-1):0),O.style,O.rotation,O.align)
                    }function ia(m,n){
                var v;fa(h.allowDecimals,true);Eb=n?1:xa.pow(10,Va(xa.log(m)/xa.LN10));v=m/Eb;n||(n=[1,2,2.5,5,10]);for(var x=0;x<n.length;x++){
                    m=n[x];if(v<=(n[x]+(n[x+1]||n[x]))/2)break
                }m*=Eb;return m
                }function S(){
                da=[];for(var m=Da.global.useUTC,n=1E3/Oa,v=6E4/Oa,x=36E5/Oa,z=864E5/Oa,K=6048E5/Oa,E=2592E6/Oa,ja=31556952E3/Oa,Z=[["second",n,[1,2,5,10,15,30]],
                    ["minute",v,[1,2,5,10,15,30]],["hour",x,[1,2,3,4,6,8,12]],["day",z,[1,2]],["week",K,[1,2]],["month",E,[1,2,3,4,6]],["year",ja,null]],eb=Z[6],O=eb[1],X=eb[2],Wa=0;Wa<Z.length;Wa++){
                    eb=Z[Wa];O=eb[1];X=eb[2];if(Z[Wa+1]){
                        var ad=(O*X[X.length-1]+Z[Wa+1][1])/2;if(Ia<=ad)break
                    }
                    }if(O==ja&&Ia<5*O)X=[1,2,5];Z=ia(Ia/O,X);var pb;X=new Date(U*Oa);X.setMilliseconds(0);if(O>=n)X.setSeconds(O>=v?0:Z*Va(X.getSeconds()/Z));if(O>=v)X[Cc](O>=x?0:Z*Va(X[mc]()/Z));if(O>=x)X[Dc](O>=z?0:Z*Va(X[kc]()/Z));if(O>=z)X[nc](O>=
                    E?1:Z*Va(X[Cb]()/Z));if(O>=E){
                    X[Ec](O>=ja?0:Z*Va(X[Kb]()/Z));pb=X[Lb]()
                    }if(O>=ja){
                    pb-=pb%Z;X[Fc](pb)
                    }O==K&&X[nc](X[Cb]()-X[lc]()+h.startOfWeek);Wa=1;n=X.getTime()/Oa;pb=X[Lb]();v=X[Kb]();for(x=X[Cb]();n<ha&&Wa<pa;){
                    da.push(n);if(O==ja)n=Mb(pb+Wa*Z,0)/Oa;else if(O==E)n=Mb(pb,v+Wa*Z)/Oa;else if(!m&&(O==z||O==K))n=Mb(pb,v,x+Wa*Z*(O==z?1:7));else n+=O*Z;Wa++
                }da.push(n);h.labels.formatter||($b=function(){
                    return Bc(h.dateTimeLabelFormats[eb[0]],this.value,1)
                    })
                }function V(){
                var m=function(x){
                    var z=(Eb<1?
                        1/Eb:1)*10;return P(x*z)/z
                    },n;n=Va(U/Ia)*Ia;var v=xa.ceil(ha/Ia)*Ia;da=[];for(n=m(n);n<=v;){
                    da.push(n);n=m(n+Ia)
                    }if(Ha){
                    U-=0.5;ha+=0.5
                    }$b||($b=function(){
                    return this.value
                    })
                }function aa(){
                ac?S():V();var m=da[0],n=da[da.length-1];if(h.startOnTick)U=m;else U>m&&da.shift();if(h.endOnTick)ha=n;else ha<n&&da.pop()
                    }function Ca(){
                if(!ac&&!Ha){
                    var m=zb,n=da.length;zb=wb[Xb];if(n<zb){
                        for(;da.length<zb;)da.push(da[da.length-1]+Ia);tb*=(n-1)/(zb-1)
                        }if(Za(m)&&zb!=m)cb.isDirty=true
                        }
                }function s(){
                var m,n,v,x=U,
                z=ha;m=h.maxZoom;var K;r();U=fa(Mc,h.min,Ka);ha=fa(Nc,h.max,Qa);if(ha-U<m){
                    K=(m-ha+U)/2;U=Zb(U-K,fa(h.min,U-K));ha=xa.min(U+m,fa(h.max,U+m))
                    }if(!Ha&&!Wb){
                    m=ha-U||1;if(!Za(h.min)&&Oc&&(Ka<0||!Jc))U-=m*Oc;if(!Za(h.max)&&Pc&&(Qa>0||!Kc))ha+=m*Pc
                        }Ia=Ha||U==ha?1:h.tickInterval=="auto"?(ha-U)*h.tickPixelInterval/Db:h.tickInterval;ac||(Ia=ia(Ia));sc=h.minorTickInterval=="auto"&&Ia?Ia/5:h.minorTickInterval;aa();tb=Db/(ha-U||1);wb||(wb={
                    x:0,
                    y:0
                });if(!ac&&da.length>wb[Xb])wb[Xb]=da.length;if(!va)for(n in db)for(v in db[n])db[n][v].cum=
                    db[n][v].total;cb.isDirty=U!=x||ha!=z
                }function ba(m,n,v){
                v=fa(v,true);na(cb,"setExtremes",{
                    min:m,
                    max:n
                },function(){
                    if(Ha){
                        if(m<0)m=0;if(n>Ha.length-1)n=Ha.length-1
                            }Mc=m;Nc=n;v&&l.redraw()
                    })
                }function B(m,n){
                Ha=m;fa(n,true)&&Xa()
                }function o(){
                return{
                    min:U,
                    max:ha,
                    dataMin:Ka,
                    dataMax:Qa
                }
                }function I(m){
                var n=m.width,v=n?tc:uc;v.push(m);n?ca(m.value,m.color,m.width):wa(m.from,m.to,m.color)
                }function ea(m){
                p([uc,tc],function(n){
                    for(var v=0;v<n.length;v++)if(n[v].id==m){
                        n.splice(v,1);break
                    }
                    });jb()
                }function Xa(){
                qb.resetTracker&&
                qb.resetTracker();jb();p(Vb,function(m){
                    m.isDirty=true
                    })
                }function jb(){
                var m=h.title,n=h.alternateGridColor,v=h.minorTickWidth,x=h.lineWidth,z,K;yb.clear();Lc.clear();if(!(!Vb.length||!Za(U)||!Za(ha))){
                    n&&p(da,function(E,ja){
                        if(ja%2==0&&E<ha)wa(E,da[ja+1]!==ma?da[ja+1]:ha,n)
                            });p(uc,function(E){
                        wa(E.from,E.to,E.color)
                        });if(sc&&!Ha)for(z=U;z<=ha;z+=sc){
                        ca(z,h.minorGridLineColor,h.minorGridLineWidth);v&&A(z,h.minorTickPosition,h.minorTickColor,v,h.minorTickLength)
                        }p(da,function(E,ja){
                        K=E+ub;ca(K,h.gridLineColor,
                            h.gridLineWidth);A(E,h.tickPosition,h.tickColor,h.tickWidth,h.tickLength,!(E==U&&!h.showFirstLabel||E==ha&&!h.showLastLabel),ja)
                        });p(tc,function(E){
                        ca(E.value,E.color,E.width)
                        });if(x){
                        v=W+(Aa?pa:0)+ob;z=Ea-Fa-(Aa?ka:0)+ob;yb.drawLine(ga?W:v,ga?z:F,ga?ib-Ba:v,ga?z:Ea-Fa,h.lineColor,x)
                        }if(m&&m.enabled&&m.text){
                        x=ga?W:F;v=ga?pa:ka;x={
                            low:x+(ga?0:v),
                            middle:x+v/2,
                            high:x+(ga?v:0)
                            }[m.align];v=(ga?F+ka:W)+(ga?1:-1)*(Aa?-1:1)*m.margin-(Na?parseInt(m.style.fontSize||m.style.font.replace(/^[a-z ]+/,""))/3:0);
                        yb.addText(m.text,ga?x:v+(Aa?pa:0)+ob,ga?v-(Aa?ka:0)+ob:x,m.style,m.rotation||0,{
                            low:"left",
                            middle:"center",
                            high:"right"
                        }[m.align])
                        }yb.strokeText();cb.isDirty=false
                    }
                }var va=h.isX,Aa=h.opposite,ga=Sa?!va:va,db={
                bar:{},
                column:{},
                area:{},
                areaspline:{}
            };t();var cb=this,ac=h.type=="datetime",ob=h.offset||0,Xb=va?"x":"y",Db=ga?pa:ka,tb,Yb=ga?W:Fa,yb=new qa("axis-layer",la,null,{
                zIndex:7
            }),Lc=new qa("grid-layer",la,null,{
                zIndex:1
            }),Ka,Qa,Vb,Mc,Nc,ha=null,U=null,Oc=h.minPadding,Pc=h.maxPadding,Jc,Kc,Wb,Qc=
            h.events,vc,uc=h.plotBands||[],tc=h.plotLines||[],Ia,sc,Eb,da,zb,$b=h.labels.formatter,Ha=h.categories||va&&l.columnCount,sb=h.reversed,ub=Ha&&h.tickmarkPlacement=="between"?0.5:0;if(Sa&&va&&sb===ma)sb=true;Aa||(ob*=-1);if(ga)ob*=-1;J(cb,{
                addPlotBand:I,
                addPlotLine:I,
                adjustTickAmount:Ca,
                categories:Ha,
                getExtremes:o,
                isXAxis:va,
                options:h,
                render:jb,
                setExtremes:ba,
                setScale:s,
                setCategories:B,
                translate:N,
                redraw:Xa,
                removePlotBand:ea,
                removePlotLine:ea,
                reversed:sb,
                stacks:db
            });for(vc in Qc)La(cb,vc,Qc[vc]);s()
            }
        function Ga(){
            function l(N,ca,wa,A){
                if(!r[N]){
                    ca=T(Ra,{
                        innerHTML:ca,
                        title:wa,
                        onclick:A
                    },J(a.toolbar.itemStyle,{
                        zIndex:1003
                    }),t.div);r[N]=ca
                    }
                }function h(N){
                ya(r[N]);r[N]=null
                }var t,r={};t=new qa("toolbar",la,null,{
                zIndex:1004,
                width:"auto",
                height:"auto"
            });return{
                add:l,
                remove:h
            }
            }function Fb(l,h){
            function t(o){
                o=o||Ya.event;if(!o.target)o.target=o.srcElement;if(!o.pageX)o.pageX=o.clientX+(sa.documentElement.scrollLeft||sa.body.scrollLeft);if(!o.pageY)o.pageY=o.clientY+(sa.documentElement.scrollTop||sa.body.scrollTop);
                return o
                }function r(o){
                var I={
                    xAxis:[],
                    yAxis:[]
                };p(Ja,function(ea){
                    var Xa=ea.translate,jb=ea.isXAxis,va=Sa?!jb:jb;I[jb?"xAxis":"yAxis"].push({
                        axis:ea,
                        value:Xa(va?o.pageX-bb.x-W:ka-o.pageY+bb.y+F,true)
                        })
                    });return I
                }function N(){
                fb.onmousedown=function(o){
                    o=t(o);o.preventDefault&&o.preventDefault();l.mouseIsDown=Gb=true;V=o.pageX;aa=o.pageY;if(Ob&&(ba||B)){
                        s||(s=T(Ra,null,{
                            position:ua,
                            border:"none",
                            background:"#4572A7",
                            opacity:0.25,
                            width:ba?0:pa+C,
                            height:B?0:ka+C
                            }));qc.div.appendChild(s)
                        }
                    };fb.onmousemove=
                function(o){
                    o=t(o);o.returnValue=false;if(Gb){
                        Ca=Math.sqrt(Math.pow(V-o.pageX,2)+Math.pow(aa-o.pageY,2))>10;if(ba){
                            var I=o.pageX-V;oa(s,{
                                width:za(I)+C,
                                left:(I>0?0:I)+V-bb.x-W+C
                                })
                            }if(B){
                            o=o.pageY-aa;oa(s,{
                                height:za(o)+C,
                                top:(o>0?0:o)+ +aa-bb.y-F+C
                                })
                            }
                        }else ca(o);return false
                    };fb.onmouseup=function(){
                    var o;if(s){
                        var I={
                            xAxis:[],
                            yAxis:[]
                        },ea=s.offsetLeft,Xa=s.offsetTop,jb=s.offsetWidth,va=s.offsetHeight;if(Ca){
                            p(Ja,function(Aa){
                                var ga=Aa.translate,db=Aa.isXAxis,cb=Sa?!db:db;I[db?"xAxis":"yAxis"].push({
                                    axis:Aa,
                                    min:ga(cb?ea:ka-Xa-va,true),
                                    max:ga(cb?ea+jb:ka-Xa,true)
                                    })
                                });na(l,"selection",I,w);o=true
                            }ya(s);s=null
                        }l.mouseIsDown=Gb=Ca=false
                    };fb.onmouseout=function(o){
                    o=o||Ya.event;if((o=o.relatedTarget||o.toElement)&&o!=bc&&o.tagName!="AREA"){
                        A();l.mouseIsDown=Gb=Ca=false
                        }
                    };fb.onclick=function(o){
                    o=t(o);o.cancelBubble=true;if(!Ca)if(S&&o.target.tagName=="AREA"){
                        var I=S.plotX,ea=S.plotY;J(S,{
                            pageX:bb.x+W+(Sa?pa-ea:I),
                            pageY:bb.y+F+(Sa?ka-I:ea)
                            });na(l.hoverSeries,"click",J(o,{
                            point:S
                        }));S.firePointEvent("click",
                            o)
                        }else{
                        J(o,r(o));na(l,"click",o)
                        }Ca=false
                    }
                }function ca(o){
                var I=l.hoverPoint,ea=l.hoverSeries;if(ea){
                    I||(I=ea.tooltipPoints[Sa?o.pageY-bb.y-F:o.pageX-bb.x-W]);if(I&&I!=S){
                        S&&S.firePointEvent("mouseOut");I.firePointEvent("mouseOver");cc.refresh(I);S=I
                        }
                    }
                }function wa(){
                var o="highchartsMap"+bd++;l.imagemap=fb=T("map",{
                    name:o,
                    id:o,
                    className:"highcharts-image-map"
                },null,la);bc=T("img",{
                    useMap:"#"+o
                    },{
                    width:pa+C,
                    height:ka+C,
                    left:W+C,
                    top:F+C,
                    opacity:0,
                    border:"none",
                    position:ua,
                    clip:"rect(1px,"+pa+"px,"+
                    ka+"px,1px)",
                    zIndex:9
                },fb);if(!Na)bc.src="data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="
                    }function A(){
                cc.hide();if(l.hoverSeries){
                    l.hoverSeries.setState();S=l.hoverSeries=null
                    }
                }function ia(o){
                var I=0,ea,Xa=fb.childNodes;for(ea=0;ea<Xa.length;ea++)if(Xa[ea].isLegendArea){
                    I=ea+1;break
                }fb.insertBefore(o,Xa[I])
                }if(h.enabled){
                var S,V,aa,Ca,s,ba=/x/.test(l.options.chart.zoomType),B=/y/.test(l.options.chart.zoomType);wa();l.tooltip=cc=kb(h);N();setInterval(function(){
                    wc&&
                    wc()
                    },32);J(this,{
                    insertAtFront:ia,
                    zoomX:ba,
                    zoomY:B,
                    resetTracker:A
                })
                }
            }function kb(l){
            function h(ia,S){
                var V=ia.tooltipPos;S=ia.series;var aa=l.borderColor||ia.color||S.color||"#606060",Ca=u.inverted,s,ba,B,o=ca.offsetHeight;B=ia.tooltipText;N=S;s=V?V[0]:Ca?pa-ia.plotY:ia.plotX;V=V?V[1]:Ca?ka-ia.plotX:ia.plotY;if(s>=0&&s<=pa&&V>=0&&V<=ka)ba=true;if(B===false||!ba)r();else{
                    ca.innerHTML=B;oa(ca,{
                        overflow:dc
                    });ba=ca.offsetWidth-wa;B=ca.offsetHeight-wa;oa(ca,{
                        overflow:$a
                    });if(ba>(A.w||0)+20||ba<(A.w||
                        0)-20||B>A.h||A.c!=aa||o!=ca.offsetHeight){
                        A.clear();A.drawRect(wa/2,wa/2,ba+20,B,aa,wa,l.borderRadius,l.backgroundColor,l.shadow);J(A,{
                            w:ba,
                            h:B,
                            c:aa
                        })
                        }aa=s-A.w+W-35;s=V-A.h+10+F;if(aa<5){
                        aa=5;s-=20
                        }if(s<5)s=5;else if(s+A.h>Ea)s=Ea-A.h-5;t(P(aa),P(s));S.drawPointState(ia,"hover");gb.style.visibility=dc
                    }
                }function t(ia,S){
                var V=gb.style.visibility==$a,aa=V?ia:(gb.offsetLeft+ia)/2;V=V?S:(gb.offsetTop+S)/2;oa(gb,{
                    left:aa+C,
                    top:V+C
                    });wc=za(ia-aa)>1||za(S-V)>1?function(){
                    t(ia,S)
                    }:null
                }function r(){
                if(gb)gb.style.visibility=
                    $a;N&&N.drawPointState()
                }var N,ca,wa=l.borderWidth,A;gb=T(Ra,null,{
                position:ua,
                visibility:$a,
                overflow:$a,
                padding:"0 50px 5px 0",
                zIndex:8
            },la);A=new qa("tooltip-box",gb,null,{
                width:ib+C,
                height:Ea+C
                });ca=T(Ra,{
                className:"highcharts-tooltip"
            },J(l.style,{
                maxWidth:ib-40+C,
                textOverflow:"ellipsis",
                position:Sb,
                zIndex:2
            }),gb);return{
                refresh:h,
                hide:r
            }
            }var ab=function(l){
            function h(Ca){
                if(Ca){
                    p(S,function(s){
                        ya(s.legendItem)
                        });S=[]
                    }p(l.series,function(s){
                    if(s.options.showInLegend){
                        var ba=s.options.legendType==
                        "point"?s.data:[s];p(ba,function(B){
                            B.simpleSymbol=/(bar|pie|area|column)/.test(s.type);B.legendItem=N=T("li",{
                                innerHTML:r.labelFormatter.call(B),
                                className:B.visible?"":Hb
                                },null,A.firstChild);if(B.options&&B.options.showCheckbox)B.checkbox=T("input",{
                                type:"checkbox",
                                checked:B.selected,
                                defaultChecked:B.selected
                                },r.itemCheckboxStyle,N);La(N,"mouseover",function(){
                                B.setState("hover")
                                });La(N,"mouseout",function(){
                                B.setState()
                                });La(N,"click",function(o){
                                o=o.target;var I="legendItemClick",ea=function(){
                                    B.setVisible()
                                    };
                                if(o.tagName=="INPUT")na(B,"checkboxClick",{
                                    checked:o.checked
                                    },function(){
                                    B.select()
                                    });else B.firePointEvent?B.firePointEvent(I,null,ea):na(B,I,null,ea)
                                    });S.push(B)
                            })
                        }
                    })
                }function t(Ca){
                if(Ca){
                    V.clear();ya(aa);aa=null
                    }if(ta.length){
                    if(r.borderWidth||r.backgroundColor)V.drawRect(A.offsetLeft,A.offsetTop,A.offsetWidth,A.offsetHeight,r.borderColor,r.borderWidth,r.borderRadius,r.backgroundColor,r.shadow);p(S,function(s){
                        if(s.legendItem){
                            var ba=s.legendItem,B=A.offsetLeft+ba.offsetLeft;ba=A.offsetTop+ba.offsetTop+
                            ba.offsetHeight/2;var o=s.legendItem.className==Hb,I=o?r.itemHiddenStyle.color:s.color;!s.simpleSymbol&&s.options&&s.options.lineWidth&&V.drawLine(B,ba,B+wa,ba,I,s.options.lineWidth);if(s.simpleSymbol)V.drawRect(B,ba-6,16,12,null,0,2,I);else if(s.options&&s.options.marker&&s.options.marker.enabled)s.drawMarker(V,B+wa/2,ba,Y(s.options.marker,o?{
                                fillColor:I,
                                lineColor:I
                            }:null))
                            }
                        });if(fb){
                        aa=T("area",{
                            shape:"rect",
                            isLegendArea:true,
                            coords:[A.offsetLeft-W,A.offsetTop-F,A.offsetLeft+A.offsetWidth-W,A.offsetTop+
                            A.offsetHeight-F].join(",")
                            });qb.insertAtFront(aa);aa.onmouseover=function(s){
                            s=s||Ya.event;s=s.relatedTarget||s.fromElement;if(s!=A&&!Gb){
                                cc.hide();oa(A,{
                                    zIndex:10
                                })
                                }
                            };A.onmouseout=aa.onmouseout=function(s){
                            s=s||Ya.event;if((s=s.relatedTarget||s.toElement)&&(s==bc||s.tagName=="AREA"&&s!=aa))oa(A,{
                                zIndex:7
                            })
                            }
                        }
                    }
                }var r=l.options.legend;if(r.enabled){
                var N,ca=r.layout,wa=r.symbolWidth,A,ia="#"+la.id+" .highcharts-legend li",S=[],V=new qa("legend",la,null,{
                    zIndex:7
                }),aa;this.dom=A=T(Ra,{
                    className:"highcharts-legend highcharts-legend-"+
                    ca,
                    innerHTML:'<ul style="margin:0;padding:0"></ul>'
                },J({
                    position:ua,
                    zIndex:7
                },r.style),la);Ab(ia,J(r.itemStyle,{
                    paddingLeft:wa+r.symbolPadding+C,
                    "float":ca=="horizontal"?"left":"none"
                    }));Ab(ia+":hover",r.itemHoverStyle);Ab(ia+"."+Hb,r.itemHiddenStyle);Ab(".highcharts-legend-horizontal li",{
                    "float":"left"
                });h();t();return{
                    renderHTML:h,
                    drawGraphics:t
                }
                }
            };Ub=Y(Ub,Da.xAxis);rc=Y(rc,Da.yAxis);Da.xAxis=Da.yAxis=null;a=Y(Da,a);var L=a.chart,M=L.margin;M=typeof M=="number"?[M,M,M,M]:M;var F=M[0],Ba=M[1],Fa=
        M[2],W=M[3],Ta,nb,la,oc,ib,Ea;D();var u=this;M=L.events;var xc,fb,cc,Gb,Hc=new qa("chart-background",la),rb,qc,ka,pa,qb,bc,Pb,bb=Nb(la),Ob,Ja=[],wb,ta=[],Tb,Sa,wc,gb,Xc={
            line:cd,
            spline:Rc,
            area:dd,
            areaspline:ed,
            column:Qb,
            bar:Gc,
            pie:fd,
            scatter:gd
        };ec=vb=0;La(Ya,"resize",function(){
            var l=sa.getElementById(oc);if(l)bb=Nb(l)
                });La(Ya,"unload",Ua);if(M)for(xc in M)La(u,xc,M[xc]);u.addLoading=function(l){
            u.resources[l]=false
            };u.clearLoading=function(l){
            u.resources[l]=true;H()
            };u.options=a;u.series=ta;u.container=
        la;u.resources={};u.inverted=Sa=a.chart.inverted;u.chartWidth=ib;u.chartHeight=Ea;u.plotWidth=pa=ib-W-Ba;u.plotHeight=ka=Ea-F-Fa;u.plotLeft=W;u.plotTop=F;u.redraw=c;u.addSeries=b;u.getSelectedPoints=k;u.getSelectedSeries=q;u.showLoading=e;u.hideLoading=f;u.get=g;u.destroy=Ua;u.updatePosition=Nb;u.plotLayer=qc=new qa("plot",la,null,{
            position:ua,
            width:pa+C,
            height:ka+C,
            left:W+C,
            top:F+C,
            overflow:$a,
            zIndex:3
        });u.tracker=qb=new Fb(u,a.tooltip);if(L.plotBackgroundImage){
            u.addLoading("plotBack");Tb=T("img");
            Tb.onload=function(){
                u.clearLoading("plotBack")
                };Tb.src=L.plotBackgroundImage
            }p(a.series,function(l){
            d(l)
            });H()
        }function Sc(a){
        for(var b=[],c=[],d=0;d<a.length;d++){
            b[d]=a[d].plotX;c[d]=a[d].plotY
            }this.xdata=b;this.ydata=c;a=[];this.y2=[];var e=c.length;this.n=e;this.y2[0]=0;this.y2[e-1]=0;a[0]=0;for(d=1;d<e-1;d++){
            var f=b[d+1]-b[d-1];f=(b[d]-b[d-1])/f;var g=f*this.y2[d-1]+2;this.y2[d]=(f-1)/g;a[d]=(c[d+1]-c[d])/(b[d+1]-b[d])-(c[d]-c[d-1])/(b[d]-b[d-1]);a[d]=(6*a[d]/(b[d+1]-b[d-1])-f*a[d-1])/g
            }for(b=
            e-2;b>=0;b--)this.y2[b]=this.y2[b]*this.y2[b+1]+a[b]
        }var ma,sa=document,Ya=window,xa=Math,P=xa.round,Va=xa.floor,Zb=xa.max,za=xa.abs,fc=xa.cos,gc=xa.sin,Q=navigator.userAgent,Na=/msie/i.test(Q)&&!Ya.opera,hd=/AppleWebKit/.test(Q),jc,bd=0,vb,ec,Tc={},pc=0,Oa=1,Jb,Ra="div",ua="absolute",Sb="relative",$a="hidden",Hb="highcharts-"+$a,dc="visible",C="px",Mb,mc,kc,lc,Cb,Kb,Lb,Cc,Dc,nc,Ec,Fc,ra=(Q=Ya.HighchartsAdapter)||{},p=ra.each,Rb=ra.grep,mb=ra.map,Y=ra.merge,Ib=ra.hyphenate,La=ra.addEvent,na=ra.fireEvent,
    xb=ra.animate,yc=ra.getAjax;if(!Q&&Ya.jQuery){
        var lb=jQuery;p=function(a,b){
            for(var c=0,d=a.length;c<d;c++)if(b.call(a[c],a[c],c,a)===false)return c
                };Rb=lb.grep;mb=function(a,b){
            for(var c=[],d=0,e=a.length;d<e;d++)c[d]=b.call(a[d],a[d],d,a);return c
            };Y=function(){
            var a=arguments;return lb.extend(true,null,a[0],a[1],a[2],a[3])
            };Ib=function(a){
            return a.replace(/([A-Z])/g,function(b,c){
                return"-"+c.toLowerCase()
                })
            };La=function(a,b,c){
            lb(a).bind(b,c)
            };na=function(a,b,c,d){
            var e=lb.Event(b),f="detached"+
            b;J(e,c);if(a[b]){
                a[f]=a[b];a[b]=null
                }lb(a).trigger(e);if(a[f]){
                a[b]=a[f];a[f]=null
                }d&&!e.isDefaultPrevented()&&d(e)
            };xb=function(a,b,c){
            lb(a).animate(b,c)
            };yc=function(a,b){
            lb.get(a,null,b)
            };lb.extend(lb.easing,{
            easeOutQuad:function(a,b,c,d,e){
                return-d*(b/=e)*(b-2)+c
                }
            })
        }else if(!Q&&Ya.MooTools){
        p=$each;mb=function(a,b){
            return a.map(b)
            };Rb=function(a,b){
            return a.filter(b)
            };Y=$merge;Ib=function(a){
            return a.hyphenate()
            };La=function(a,b,c){
            if(!a.addEvent)if(a.nodeName)a=$(a);else J(a,new Events);a.addEvent(b,
                c)
            };na=function(a,b,c,d){
            b=new Event({
                type:b,
                target:a
            });b=J(b,c);b.preventDefault=function(){
                d=null
                };a.fireEvent&&a.fireEvent(b.type,b);d&&d(b)
            };xb=function(a,b,c){
            a=new Fx.Morph($(a),J(c,{
                transition:Fx.Transitions.Quad.easeInOut
                }));a.start(b)
            };yc=function(a,b){
            (new Request({
                url:a,
                method:"get",
                onSuccess:b
            })).send()
            }
        }Q='normal 12px "Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif';ra={
        enabled:true,
        align:"center",
        x:0,
        y:15,
        style:{
            color:"#666",
            font:Q.replace("12px","11px")
            }
        };
    var Da={
        colors:["#4572A7","#AA4643","#89A54E","#80699B","#3D96AE","#DB843D","#92A8CD","#A47D7C","#B5CA92"],
        symbols:["circle","diamond","square","triangle","triangle-down"],
        lang:{
            loading:"Loading...",
            months:["January","February","March","April","May","June","July","August","September","October","November","December"],
            weekdays:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],
            decimalPoint:".",
            resetZoom:"Reset zoom",
            resetZoomTitle:"Reset zoom level 1:1",
            thousandsSep:","
        },
        global:{
            useUTC:true
        },
        chart:{
            margin:[50,50,60,80],
            borderColor:"#4572A7",
            borderRadius:5,
            defaultSeriesType:"line",
            ignoreHiddenSeries:true,
            plotBorderColor:"#C0C0C0"
        },
        title:{
            text:"Chart title",
            style:{
                textAlign:"center",
                color:"#3E576F",
                font:Q.replace("12px","16px"),
                margin:"10px 0 0 0"
            }
            },
        subtitle:{
            text:"",
            style:{
                textAlign:"center",
                color:"#6D869F",
                font:Q,
                margin:0
            }
            },
        plotOptions:{
            line:{
                allowPointSelect:false,
                showCheckbox:false,
                animation:true,
                events:{},
                lineWidth:2,
                shadow:true,
                marker:{
                    enabled:true,
                    symbol:"auto",
                    lineWidth:0,
                    radius:4,
                    lineColor:"#FFFFFF",
                    fillColor:"auto",
                    states:{
                        hover:{},
                        select:{
                            fillColor:"#FFFFFF",
                            lineColor:"auto",
                            lineWidth:2
                        }
                        }
                    },
                point:{
                    events:{}
                },
                dataLabels:Y(ra,{
                    enabled:false,
                    y:-6,
                    formatter:function(){
                        return this.y
                        }
                    }),
                showInLegend:true,
                states:{
                    hover:{
                        lineWidth:3,
                        marker:{}
                    },
                    select:{
                        marker:{}
                    }
                    }
                }
            },
        labels:{
            style:{
                position:ua,
                color:"#3E576F",
                font:Q
            }
            },
        legend:{
            enabled:true,
            layout:"horizontal",
            labelFormatter:function(){
                return this.name
                },
            borderColor:"#909090",
            borderRadius:5,
            shadow:true,
            style:{
                bottom:"10px",
                left:"80px",
                padding:"5px"
            },
            itemStyle:{
                listStyle:"none",
                margin:0,
                padding:"0 2em 0 0",
                font:Q,
                cursor:"pointer",
                color:"#3E576F",
                position:Sb
            },
            itemHoverStyle:{
                color:"#000"
            },
            itemHiddenStyle:{
                color:"#CCC"
            },
            itemCheckboxStyle:{
                position:ua,
                right:0
            },
            symbolWidth:16,
            symbolPadding:5
        },
        loading:{
            hideDuration:100,
            labelStyle:{
                font:Q.replace("normal","bold"),
                position:Sb,
                top:"1em"
            },
            showDuration:100,
            style:{
                position:ua,
                backgroundColor:"white",
                opacity:0.5,
                textAlign:"center"
            }
            },
        tooltip:{
            enabled:true,
            formatter:function(){
                return"<b>"+(this.point.name||
                    this.series.name)+"</b><br/>X value: "+this.x+"<br/>Y value: "+this.y
                },
            backgroundColor:"rgba(255, 255, 255, .85)",
            borderWidth:2,
            borderRadius:5,
            shadow:true,
            snap:10,
            style:{
                color:"#333333",
                font:Q,
                fontSize:"9pt",
                padding:"5px",
                whiteSpace:"nowrap"
            }
            },
        toolbar:{
            itemStyle:{
                color:"#4572A7",
                cursor:"pointer",
                margin:"20px",
                font:Q
            }
            },
        credits:{
            enabled:true,
            text:"Highcharts.com",
            href:"http://www.highcharts.com",
            style:{
                position:ua,
                right:"10px",
                bottom:"5px",
                color:"#999",
                textDecoration:"none",
                font:Q.replace("12px","10px")
                },
            target:"_self"
        }
        },Ub={
        dateTimeLabelFormats:{
            second:"%H:%M:%S",
            minute:"%H:%M",
            hour:"%H:%M",
            day:"%e. %b",
            week:"%e. %b",
            month:"%b '%y",
            year:"%Y"
        },
        endOnTick:false,
        gridLineColor:"#C0C0C0",
        labels:ra,
        lineColor:"#C0D0E0",
        lineWidth:1,
        max:null,
        min:null,
        maxZoom:null,
        minorGridLineColor:"#E0E0E0",
        minorGridLineWidth:1,
        minorTickColor:"#A0A0A0",
        minorTickLength:2,
        minorTickPosition:"outside",
        minorTickWidth:1,
        showFirstLabel:true,
        showLastLabel:false,
        startOfWeek:1,
        startOnTick:false,
        tickColor:"#C0D0E0",
        tickInterval:"auto",
        tickLength:5,
        tickmarkPlacement:"between",
        tickPixelInterval:100,
        tickPosition:"outside",
        tickWidth:1,
        title:{
            enabled:false,
            text:"X-values",
            align:"middle",
            margin:35,
            style:{
                color:"#6D869F",
                font:Q.replace("normal","bold")
                }
            },
        type:"linear"
    },rc=Y(Ub,{
        endOnTick:true,
        gridLineWidth:1,
        tickPixelInterval:72,
        showLastLabel:true,
        labels:{
            align:"right",
            x:-8,
            y:3
        },
        lineWidth:0,
        maxPadding:0.05,
        minPadding:0.05,
        startOnTick:true,
        tickWidth:0,
        title:{
            enabled:true,
            margin:40,
            rotation:270,
            text:"Y-values"
        }
        }),$c={
        labels:{
            align:"right",
            x:-8,
            y:3
        },
        title:{
            rotation:270
        }
        },Zc={
        labels:{
            align:"left",
            x:8,
            y:3
        },
        title:{
            rotation:90
        }
        },Ic={
        labels:{
            align:"center",
            x:0,
            y:14
        },
        title:{
            rotation:0
        }
        },Yc=Y(Ic,{
        labels:{
            y:-5
        }
        });Q=Da.plotOptions;ra=Q.line;Q.spline=Y(ra);Q.scatter=Y(ra,{
        lineWidth:0,
        states:{
            hover:{
                lineWidth:0
            }
            }
        });Q.area=Y(ra,{
        fillColor:"auto"
    });Q.areaspline=Y(Q.area);Q.column=Y(ra,{
        borderColor:"#FFFFFF",
        borderWidth:1,
        borderRadius:0,
        groupPadding:0.2,
        pointPadding:0.1,
        states:{
            hover:{
                brightness:0.1,
                shadow:false
            },
            select:{
                color:"#C0C0C0",
                borderColor:"#000000",
                shadow:false
            }
            }
        });Q.bar=Y(Q.column,{
        dataLabels:{
            align:"left",
            x:5,
            y:0
        }
        });Q.pie=Y(ra,{
        borderColor:"#FFFFFF",
        borderWidth:1,
        center:["50%","50%"],
        legendType:"point",
        size:"90%",
        slicedOffset:10,
        states:{
            hover:{
                brightness:0.1,
                shadow:false
            }
            }
        });Ac();var zc=function(a){
        function b(i){
            if(g=/rgba\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]+(?:\.[0-9]+)?)\s*\)/.exec(i))f=[parseInt(g[1]),parseInt(g[2]),parseInt(g[3]),parseFloat(g[4])];else if(g=/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(i))f=
                [parseInt(g[1],16),parseInt(g[2],16),parseInt(g[3],16),1]
                }function c(){
            return f&&!isNaN(f[0])?"rgba("+f.join(",")+")":a
            }function d(i){
            if(typeof i=="number"&&i!=0)for(var j=0;j<3;j++){
                f[j]+=parseInt(i*255);if(f[j]<0)f[j]=0;if(f[j]>255)f[j]=255
                    }return this
            }function e(i){
            f[3]=i;return this
            }var f=[],g;b(a);return{
            get:c,
            brighten:d,
            setOpacity:e
        }
        },qa=function(a,b,c,d){
        var e=this,f=b.style;c=J({
            className:"highcharts-"+a
            },c);d=J({
            width:f.width,
            height:f.height,
            position:ua,
            top:0,
            left:0,
            margin:0,
            padding:0,
            border:"none"
        },
        d);a=T(Ra,c,d,b);J(e,{
            div:a,
            width:parseInt(d.width),
            height:parseInt(d.height)
            });e.svg=Na?"":'<?xml version="1.0" encoding="utf-8"?><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="'+e.width+'px" height="'+e.height+'">';e.basicSvg=e.svg
        };qa.prototype={
        getCtx:function(){
            if(!this.ctx){
                var a=T("canvas",{
                    id:"highcharts-canvas-"+pc++,
                    width:this.width,
                    height:this.height
                    },{
                    position:ua
                },this.div);if(Na){
                     G_vmlCanvasManager.initElement(a);a=sa.getElementById(a.id);
                    }this.ctx=
                a.getContext("2d")
                }return this.ctx
            },
        getSvg:function(){
            if(!this.svgObject){
                var a=this,b=a.div,c=a.width;a=a.height;if(Na){
                    if(!sa.namespaces.g_vml_){
                        sa.namespaces.add("g_vml_","urn:schemas-microsoft-com:vml");sa.createStyleSheet().cssText="g_vml_\\:*{behavior:url(#default#VML)}"
                        }this.svgObject=T(Ra,null,{
                        width:c+C,
                        height:a+C,
                        position:ua
                    },b)
                    }else this.svgObject=T("object",{
                    width:c,
                    height:a,
                    type:"image/svg+xml"
                },{
                    position:ua,
                    left:0,
                    top:0
                },b)
                }return this.svgObject
            },
        drawLine:function(a,b,c,d,e,f){
            var g=
            this.getCtx();if(a==c)a=c=P(a)+f%2/2;if(b==d)b=d=P(b)+f%2/2;g.lineWidth=f;g.lineCap="round";g.beginPath();g.moveTo(a,b);g.strokeStyle=e;g.lineTo(c,d);g.closePath();g.stroke()
            },
        drawPolyLine:function(a,b,c,d,e){
            var f=this.getCtx(),g=[];if(d&&c){
                p(a,function(i){
                    g.push(i===ma?i:i+1)
                    });for(d=1;d<=3;d++)this.drawPolyLine(g,"rgba(0, 0, 0, "+0.05*d+")",6-2*d)
                    }f.beginPath();for(d=0;d<a.length;d+=2)f[d==0?"moveTo":"lineTo"](a[d],a[d+1]);J(f,{
                lineWidth:c,
                lineJoin:"round"
            });if(b&&c){
                f.strokeStyle=Bb(b,f);f.stroke()
                }if(e){
                f.fillStyle=
                Bb(e,f);f.fill()
                }
            },
        drawRect:function(a,b,c,d,e,f,g,i,j,k){
            var q=function(){
                var H;if(c>0&&d>0){
                    w.beginPath();if(g){
                        w.moveTo(a,b+g);w.lineTo(a,b+d-g);w.quadraticCurveTo(a,b+d,a+g,b+d);w.lineTo(a+c-g,b+d);w.quadraticCurveTo(a+c,b+d,a+c,b+d-g);w.lineTo(a+c,b+g);w.quadraticCurveTo(a+c,b,a+c-g,b);w.lineTo(a+g,b);w.quadraticCurveTo(a,b,a,b+g)
                        }else w.rect(a,b,c,d);w.closePath();H=true
                    }return H
                },w=this.getCtx(),y=(f||0)%2/2;a=P(a)+y;b=P(b)+y;c=P(c-2*y);d=P(d-2*y);if(j)for(j=1;j<=3;j++)this.drawRect(a+1,b+
                1,c,d,"rgba(0, 0, 0, "+0.05*j+")",6-2*j,g);k&&w.drawImage(k,a,b,c,d);if(q()){
                if(i){
                    w.fillStyle=Bb(i,w);w.fill();Ya.G_vmlCanvasManager&&q()
                    }if(f){
                    w.strokeStyle=Bb(e,w);w.lineWidth=f;w.stroke()
                    }
                }
            },
        drawSymbol:function(a,b,c,d,e,f,g){
            var i=this.getCtx(),j=/^url\((.*?)\)$/;i.beginPath();if(a=="square"){
                a=0.707*d;i.moveTo(b-a,c-a);i.lineTo(b+a,c-a);i.lineTo(b+a,c+a);i.lineTo(b-a,c+a);i.lineTo(b-a,c-a)
                }else if(a=="triangle"){
                c++;i.moveTo(b,c-1.33*d);i.lineTo(b+d,c+0.67*d);i.lineTo(b-d,c+0.67*d);i.lineTo(b,
                    c-1.33*d)
                }else if(a=="triangle-down"){
                c--;i.moveTo(b,c+1.33*d);i.lineTo(b-d,c-0.67*d);i.lineTo(b+d,c-0.67*d);i.lineTo(b,c+1.33*d)
                }else if(a=="diamond"){
                i.moveTo(b,c-d);i.lineTo(b+d,c);i.lineTo(b,c+d);i.lineTo(b-d,c);i.lineTo(b,c-d)
                }else j.test(a)?T("img",{
                onload:function(){
                    var k=this,q=Tc[k.src]||[k.width,k.height];oa(k,{
                        left:P(b-q[0]/2)+C,
                        top:P(c-q[1]/2)+C,
                        visibility:dc
                    });Tc[k.src]=q
                    },
                src:a.match(j)[1]
                },{
                position:ua,
                visibility:Na?dc:$a
                },this.div):i.arc(b,c,d,0,2*xa.PI,true);if(g){
                i.fillStyle=g;i.fill()
                }if(f&&
                e){
                i.strokeStyle=f||"rgb(100, 100, 255)";i.lineWidth=e||2;i.stroke()
                }
            },
        drawHtml:function(a,b,c){
            T(Ra,J(b,{
                innerHTML:a
            }),J(c,{
                position:ua
            }),this.div)
            },
        drawText:function(){
            this.addText.apply(this,arguments);this.strokeText()
            },
        addText:function(a,b,c,d,e,f){
            if(a||a===0){
                var g=this,i,j=g.div,k,q="";d=d||{};var w=d.color||"#000000";f=f||"left";var y=parseInt(d.fontSize||d.font.replace(/^[a-z ]+/,""));for(var H in d)q+=Ib(H)+":"+d[H]+";";p(["MozTransform","WebkitTransform","transform"],function(Ua){
                    if(Ua in
                        j.style)k=Ua
                        });if(!e||k){
                    a=T("span",{
                        innerHTML:a
                    },J(d,{
                        position:ua,
                        left:b+C,
                        whiteSpace:"nowrap",
                        bottom:P(g.height-c-y*0.25)+C,
                        color:w
                    }),j);q=a.offsetWidth;if(f=="right")oa(a,{
                        left:b-q+C
                        });else f=="center"&&oa(a,{
                        left:P(b-q/2)+C
                        });if(e){
                        f={
                            left:0,
                            center:50,
                            right:100
                        }[f];a.style[k]="rotate("+e+"deg)";a.style[k+"Origin"]=f+"% 100%"
                        }
                    }else if(Na){
                    i=true;d=(e||0)*xa.PI*2/360;e=fc(d);d=gc(d);H=g.width;y=y/3||3;var G=f=="left",D=f=="right",R=G?b:b-H*e;b=D?b:b+H*e;G=G?c:c-H*d;c=D?c:c+H*d;R+=y*d;b+=y*d;G-=
                    y*e;c-=y*e;if(za(R-b)<0.1)R+=0.1;if(za(G-c)<0.1)G+=0.1;g.svg+='<g_vml_:line from="'+R+", "+G+'" to="'+b+", "+c+'" stroked="false"><g_vml_:fill on="true" color="'+w+'"/><g_vml_:path textpathok="true"/><g_vml_:textpath on="true" string="'+a+'" style="v-text-align:'+f+";"+q+'"/></g_vml_:line>'
                    }else{
                    i=true;g.svg+='<g><text transform="translate('+b+","+c+") rotate("+(e||0)+')" style="fill:'+w+";text-anchor:"+{
                        left:"start",
                        center:"middle",
                        right:"end"
                    }[f]+";"+q.replace(/"/g,"'")+'">'+a+"</text></g>"
                    }g.hasObject=
                i
                }
            },
        strokeText:function(){
            if(this.hasObject){
                var a=this.getSvg(),b=this.svg;if(Na)a.innerHTML=b;else{
                    a.data="data:image/svg+xml,"+b+"</svg>";hd&&this.div.appendChild(a)
                    }
                }
            },
        clear:function(){
            var a=this,b=this.div;b=b.childNodes;a.ctx&&a.ctx.clearRect(0,0,a.width,a.height);if(a.svgObject){
                ya(a.svgObject);a.svgObject=null;a.svg=a.basicSvg
                }for(var c=b.length-1;c>=0;c--){
                a=b[c];a.tagName=="SPAN"&&ya(a)
                }
            },
        hide:function(){
            oa(this.div,{
                display:"none"
            })
            },
        show:function(){
            oa(this.div,{
                display:""
            })
            },
        destroy:function(){
            ya(this.div);
            return null
            }
        };var hc=function(){};hc.prototype={
        init:function(a,b){
            var c=this;c.series=a;c.applyOptions(b);return c
            },
        applyOptions:function(a){
            var b=this,c=b.series;if(typeof a=="number"||a===null)b.y=a;else if(typeof a=="object"&&typeof a.length!="number"){
                J(b,a);b.options=a
                }else if(typeof a[0]=="string"){
                b.name=a[0];b.y=a[1]
                }else if(typeof a[0]=="number"){
                b.x=a[0];b.y=a[1]
                }if(b.x===ma)b.x=c.autoIncrement()
                },
        destroy:function(){
            var a=this;a.stateLayer&&a.stateLayer.destroy();for(prop in a)a[prop]=
                null
                },
        select:function(a,b){
            var c=this,d=c.series,e=d.chart,f,g,i=fa(c.stateLayer,d.singlePointLayer,e.singlePointLayer);c.selected=a=fa(a,!c.selected);d.isDirty=true;c.firePointEvent(a?"select":"unselect");i&&i.clear();p(e.series,function(j){
                f=j.stateLayers;b||p(j.data,function(k){
                    if(k.selected&&k!=c){
                        k.selected=false;na(k,"unselect");j.isDirty=true
                        }
                    });if(j.isDirty){
                    for(g in f)f[g].clear();j.render()
                    }
                })
            },
        update:function(a,b){
            var c=this,d=c.series;b=fa(b,true);c.firePointEvent("update",{
                options:a
            },
            function(){
                c.applyOptions(a);d.isDirty=true;b&&d.chart.redraw()
                })
            },
        remove:function(a){
            var b=this,c=b.series,d=c.chart,e=c.data;a=fa(a,true);b.firePointEvent("remove",null,function(){
                p(e,function(f,g){
                    f==b&&e.splice(g,1)
                    });if(b.layer)b.layer=b.layer.destroy();if(b.legendItem){
                    ya(b.legendItem);b.legendItem=null;d.isDirty=true
                    }c.isDirty=true;a&&d.redraw()
                })
            },
        firePointEvent:function(a,b,c){
            var d=this,e=this.series;e=e.options;if(e.point.events[a]||d.options&&d.options.events&&d.options.events[a])this.importEvents();
            if(a=="click"&&e.allowPointSelect)c=function(f){
                d.select(null,f.ctrlKey||f.metaKey||f.shiftKey)
                };na(this,a,b,c)
            },
        importEvents:function(){
            if(!this.hasImportedEvents){
                var a=this,b=Y(a.series.options.point,a.options);b=b.events;var c;a.events=b;for(c in b)La(a,c,b[c]);this.hasImportedEvents=true
                }
            },
        setTooltipText:function(){
            var a=this;a.tooltipText=a.series.chart.options.tooltip.formatter.call({
                series:a.series,
                point:a,
                x:a.category,
                y:a.y,
                percentage:a.percentage
                })
            }
        };var Ma=function(){
        this.isCartesian=true;
        this.type="line";this.pointClass=hc
        };Ma.prototype={
        init:function(a,b){
            var c=this,d,e=a.series.length;c.chart=a;b=c.setOptions(b);J(c,{
                index:e,
                options:b,
                name:b.name||"Series "+(e+1),
                state:"",
                visible:b.visible!==false,
                selected:b.selected==true
                });a=b.events;for(d in a)La(c,d,a[d]);c.getColor();c.getSymbol();c.getData(b)
            },
        getData:function(a){
            var b=this,c=b.chart,d="series"+pc++;if(!a.data&&a.dataURL){
                c.addLoading(d);yc(a.dataURL,function(e){
                    b.dataLoaded(e);c.clearLoading(d)
                    })
                }else b.dataLoaded(a.data)
                },
        dataLoaded:function(a){
            var b=this,c=b.chart,d=b.options,e=[""],f=d.dataParser,g={},i;if(d.dataURL&&!f)f=function(j){
                return eval(j)
                };if(f)a=f.call(b,a);b.layerGroup=i=new qa("series-group",c.plotLayer.div,null,{
                zIndex:2
            });d.states.hover.enabled&&e.push("hover");p(e,function(j){
                g[j]=new qa("state-"+j,i.div)
                });b.stateLayers=g;b.setData(a,false)
            },
        autoIncrement:function(){
            var a=this,b=a.options,c=a.xIncrement;c=fa(c,b.pointStart,0);a.pointInterval=fa(a.pointInterval,b.pointInterval,1);a.xIncrement=c+a.pointInterval;
            return c
            },
        cleanData:function(){
            var a=this;a=a.data;var b;a.sort(function(c,d){
                return c.x-d.x
                });for(b=a.length-1;b>=0;b--)a[b-1]&&a[b-1].x==a[b].x&&a.splice(b-1,1)
                },
        getSegments:function(){
            var a=-1,b=[],c=this.data;p(c,function(d,e){
                if(d.y===null){
                    e>a+1&&b.push(c.slice(a+1,e));a=e
                    }else e==c.length-1&&b.push(c.slice(a+1,e+1))
                    });this.segments=b
            },
        setOptions:function(a){
            var b=this.chart.options.plotOptions;a=Y(b[this.type],b.series,a);b=a.marker;var c=a.states.hover.marker;if(c.lineWidth===ma)c.lineWidth=
                b.lineWidth+1;if(c.radius===ma)c.radius=b.radius+1;return a
            },
        getColor:function(){
            var a=this.chart.options.colors;this.color=this.options.color||a[vb++]||"#0000ff";if(vb>=a.length)vb=0
                },
        getSymbol:function(){
            var a=this.chart.options.symbols,b=this.options.marker.symbol||"auto";if(b=="auto")b=a[ec++];this.symbol=b;if(ec>=a.length)ec=0
                },
        addPoint:function(a,b,c){
            var d=this,e=d.data;a=(new d.pointClass).init(d,a);b=fa(b,true);e.push(a);c&&e.shift();d.isDirty=true;b&&d.chart.redraw()
            },
        setData:function(a,
            b){
            var c=this;c.xIncrement=null;a=mb(ic(a),function(d){
                return(new c.pointClass).init(c,d)
                });c.data=a;c.cleanData();c.getSegments();c.isDirty=true;fa(b,true)&&c.chart.redraw()
            },
        remove:function(a){
            var b=this,c=b.chart;a=fa(a,true);if(!b.isRemoving){
                b.isRemoving=true;na(b,"remove",null,function(){
                    ya(b.layerGroup.div);p(b.areas,function(d){
                        ya(d)
                        });ya(b.legendItem);b.legendItem=null;p(c.series,function(d,e){
                        d==b&&c.series.splice(e,1)
                        });c.isDirty=true;a&&c.redraw()
                    })
                }b.isRemoving=false
            },
        translate:function(){
            var a=
            this.chart,b=this,c=b.options.stacking,d=b.xAxis.categories,e=b.yAxis,f=e.stacks[b.type];p(this.data,function(g){
                var i=g.x,j=g.y,k;g.plotX=b.xAxis.translate(g.x);if(c&&b.visible){
                    k=f[i];i=k.total;k.cum=k=k.cum-j;j=k+j;if(c=="percent"){
                        k=i?k*100/i:0;j=i?j*100/i:0
                        }g.percentage=i?g.y*100/i:0;g.stackTotal=i;g.yBottom=e.translate(k,0,1)
                    }if(j!==null)g.plotY=e.translate(j,0,1);g.clientX=a.inverted?a.plotHeight-g.plotX+a.plotTop:g.plotX+a.plotLeft;g.category=d&&d[g.x]!==ma?d[g.x]:g.x
                })
            },
        setTooltipPoints:function(a){
            var b=
            this,c=b.chart,d=c.inverted,e=[],f=d?c.plotHeight:c.plotWidth,g,i,j=[];if(a)b.tooltipPoints=null;p(b.segments,function(k){
                e=e.concat(k)
                });if(b.xAxis.reversed)e=e.reverse();p(e,function(k,q){
                b.tooltipPoints||k.setTooltipText();g=e[q-1]?e[q-1].high+1:0;for(i=k.high=e[q+1]?Va((k.plotX+(e[q+1]?e[q+1].plotX:f))/2):f;g<=i;)j[d?f-g++:g++]=k
                    });b.tooltipPoints=j
            },
        drawLine:function(a){
            var b=this,c=b.options,d=b.chart,e=c.animation&&b.animate,f=b.stateLayers[a],g=c.lineColor||b.color,i=c.fillColor=="auto"?zc(b.color).setOpacity(c.fillOpacity||
                0.75).get():c.fillColor,j=d.inverted,k=(j?0:d.plotHeight)-b.yAxis.translate(0);if(a)c=Y(c,c.states[a]);e&&b.animate(true);p(b.segments,function(q){
                var w=[],y=[];p(q,function(G){
                    w.push(j?d.plotWidth-G.plotY:G.plotX,j?d.plotHeight-G.plotX:G.plotY)
                    });if(/area/.test(b.type)){
                    for(var H=0;H<w.length;H++)y.push(w[H]);if(c.stacking&&b.type!="areaspline")for(H=q.length-1;H>=0;H--)y.push(q[H].plotX,q[H].yBottom);else y.push(j?k:q[q.length-1].plotX,j?d.plotHeight-q[q.length-1].plotX:k,j?k:q[0].plotX,j?d.plotHeight-
                        q[0].plotX:k);f.drawPolyLine(y,null,null,c.shadow,i)
                    }c.lineWidth&&f.drawPolyLine(w,g,c.lineWidth,c.shadow)
                });e&&b.animate()
            },
        animate:function(a){
            var b=this,c=b.chart,d=c.inverted,e=b.layerGroup.div;if(b.visible)if(a)oa(e,J({
                overflow:$a
            },d?{
                height:0
            }:{
                width:0
            }));else{
                xb(e,d?{
                    height:c.plotHeight+C
                    }:{
                    width:c.plotWidth+C
                    },{
                    duration:1E3
                });this.animate=null
                }
            },
        drawPoints:function(a){
            var b=this,c=b.stateLayers[a];a=b.options;var d=a.marker;a=b.data;var e=b.chart,f=e.inverted;d.enabled&&p(a,function(g){
                if(g.plotY!==
                    ma)b.drawMarker(c,f?e.plotWidth-g.plotY:g.plotX,f?e.plotHeight-g.plotX:g.plotY,Y(d,g.marker));g.selected&&b.drawPointState(g,"select",c)
                })
            },
        drawMarker:function(a,b,c,d){
            if(d.lineColor=="auto")d.lineColor=this.color;if(d.fillColor=="auto")d.fillColor=this.color;if(d.symbol=="auto")d.symbol=this.symbol;a.drawSymbol(d.symbol,b,c,d.radius,d.lineWidth,d.lineColor,d.fillColor)
            },
        drawDataLabels:function(){
            if(this.options.dataLabels.enabled){
                var a=this,b,c,d=a.data,e=a.options.dataLabels,f,g,i=a.chart,j=i.inverted,
                k=a.type,q=k=="pie",w;a.dataLabelsLayer=g=new qa("data-labels",a.layerGroup.div,null,{
                    zIndex:1
                });e.style.color=e.color=="auto"?a.color:e.color;p(d,function(y){
                    var H=y.plotX,G=y.plotY,D=y.tooltipPos;f=e.formatter.call({
                        x:y.x,
                        y:y.y,
                        series:a,
                        point:y
                    });b=(j?i.plotWidth-G:H)+e.x;c=(j?i.plotHeight-H:G)+e.y;if(D){
                        b=D[0]+e.x;c=D[1]+e.y
                        }if(q){
                        if(!y.dataLabelsLayer)y.dataLabelsLayer=new qa("data-labels",y.layer.div,null,{
                            zIndex:3
                        });g=y.dataLabelsLayer
                        }w=e.align;if(k=="column")b+={
                        center:y.w/2,
                        right:y.w
                        }[w]||
                    0;if(f)g[q?"drawText":"addText"](f,b,c,e.style,e.rotation,w)
                        });q||g.strokeText()
                }
            },
        drawPointState:function(a,b,c){
            var d=this.chart,e=d.inverted,f=b=="hover";c=c||d.singlePointLayer;var g=this.options;if(f){
                if(!c)c=d.singlePointLayer=new qa("single-point",d.plotLayer.div,null,{
                    zIndex:3
                });c.clear()
                }if(b){
                var i=g.states[b].marker;b=g.marker.states[b];if(f&&b.radius===ma)b.radius=i.radius+2;if((f=Y(g.marker,a.marker,i,b))&&f.enabled)this.drawMarker(c,e?d.plotWidth-a.plotY:a.plotX,e?d.plotHeight-a.plotX:
                    a.plotY,f)
                }
            },
        destroy:function(){
            var a=this,b;p(a.data,function(c){
                c.destroy()
                });for(b in a)a[b]=null
                },
        render:function(){
            var a=this,b,c=a.stateLayers;a.drawDataLabels();if(a.visible)for(b in c){
                a.drawLine(b);a.drawPoints(b)
                }else a.setVisible(false,false);if(!a.hasRendered&&c.hover){
                c.hover.hide();hasRendered=true
                }a.isDirty=false
            },
        redraw:function(){
            var a=this;a.translate();a.setTooltipPoints(true);a.chart.options.tooltip.enabled&&a.createArea();a.clear();a.render()
            },
        clear:function(){
            var a=this.stateLayers;
            for(var b in a){
                a[b].clear();a[b].cleared=true
                }if(this.dataLabelsLayer){
                this.dataLabelsLayer.clear();this.hasDrawnDataLabels=false
                }
            },
        setState:function(a){
            a=a||"";if(this.state!=a){
                var b=this,c=b.stateLayers,d=c[a];c=c[b.state];var e=b.singlePointLayer||b.chart.singlePointLayer;b.state=a;if(d)if(a)d.show();else{
                    c&&c.hide();e&&e.clear()
                    }
                }
            },
        setVisible:function(a,b){
            var c=this,d=c.chart,e=c.layerGroup,f=c.legendItem,g=c.areas,i=c.visible;if(c.visible=a=a===ma?!i:a){
                c.isDirty=true;e.show()
                }else e.hide();
            if(f){
                f.className=a?"":Hb;d.legend.drawGraphics(true)
                }g&&p(g,function(j){
                a?d.tracker.insertAtFront(j):ya(j)
                });d.options.chart.ignoreHiddenSeries&&c.options.stacking&&p(d.series,function(j){
                if(j.options.stacking&&j.visible)j.isDirty=true
                    });b!==false&&d.redraw();na(c,a?"show":"hide")
            },
        show:function(){
            this.setVisible(true)
            },
        hide:function(){
            this.setVisible(false)
            },
        select:function(a){
            var b=this;b.selected=a=a===ma?!b.selected:a;if(b.checkbox)b.checkbox.checked=a;na(b,a?"select":"unselect")
            },
        getAreaCoords:function(){
            var a=
            this,b=this.chart,c=b.inverted,d=b.plotWidth,e=b.plotHeight,f=a.xAxis.reversed,g,i=b.options.tooltip.snap,j=[];p(a.splinedata||a.segments,function(k,q){
                if((g=k.length>1&&k[0].x>k[1].x)&&!f||f&&!g)k=k.reverse();var w=[],y=[],H=[];p([y,H],function(G){
                    for(var D=0,R=0,Ua,Pa,Ga=[k[0]],Fb=G==y?1:-1,kb,ab,L,M,F,Ba,Fa;k[R];){
                        if(k[R].plotX>k[D].plotX+i||R==k.length-1){
                            Ua=k[R];Pa=k.slice(D,R-1);p(Pa,function(W){
                                if(Fb*W.plotY<Fb*Ua.plotY)Ua=W
                                    });if(P(k[D].plotX)<P(Ua.plotX)||k[R].plotX>k[D].plotX+i)Ga.push(Ua);
                            D=R
                            }R++
                    }Ga[Ga.length-1]!=k[k.length-1]&&Ga.push(k[k.length-1]);for(R=0;R<Ga.length;R++)if(R>0){
                        ab=Ga[R].plotX;kb=Ga[R].plotY;D=Ga[R-1].plotX;Pa=Ga[R-1].plotY;M=ab-Ga[R-1].plotX;Ba=F=kb-Ga[R-1].plotY;L=-M;Fa=xa.sqrt(xa.pow(Ba,2)+xa.pow(L,2));if(R==1){
                            D-=i/Fa*M;Pa-=i/Fa*F
                            }else if(R==Ga.length-1){
                            ab+=i/Fa*M;kb+=i/Fa*F
                            }M=Fb*i/Fa;D=P(D+M*Ba);Pa=P(Pa+M*L);ab=P(ab+M*Ba);L=P(kb+M*L);if(G[G.length-1]&&G[G.length-1][0]>D)for(kb=false;!kb;){
                            F=G.pop();Ba=G[G.length-1];if(!Ba)break;M=(Pa-L)/(D-ab);F=(Ba[1]-F[1])/
                            (Ba[0]-F[0]);F=(-F*Ba[0]+Ba[1]+M*D-Pa)/(M-F);M=M*(F-D)+Pa;if(F>Ba[0]){
                                G.push([P(F),P(M),1]);kb=true
                                }
                            }else isNaN(D)||G.push([D,Pa]);G[G.length-1]&&G[G.length-1][0]<ab&&G.push([ab,L])
                        }
                    });for(q=0;q<y.length;q++)w.push(c?d-y[q][1]:y[q][0],c?e-y[q][0]:y[q][1]);for(q=H.length-1;q>=0;q--)w.push(c?d-H[q][1]:H[q][0],c?e-H[q][0]:H[q][1]);!w.length&&k[0]&&w.push(P(k[0].plotX),P(k[0].plotY));w.length&&j.push([w.join(",")])
                });return j
            },
        createArea:function(){
            if(this.options.enableMouseTracking!==false){
                var a,b=
                this,c=b.options,d=b.chart,e=d.tracker,f=b.getAreaCoords(),g=[],i=b.areas,j;i&&p(i,function(k){
                    ya(k)
                    });p(f,function(k){
                    j=/^[0-9]+,[0-9]+$/.test(k[0]);a=T("area",{
                        shape:j?"circle":"poly",
                        chart:d,
                        coords:k[0]+(j?","+d.options.tooltip.snap:""),
                        onmouseover:function(){
                            if(!(!b.visible||d.mouseIsDown)){
                                var q=d.hoverSeries;d.hoverPoint=k[1];c.events.mouseOver&&na(b,"mouseOver",{
                                    point:d.hoverPoint
                                    });q&&q!=b&&q.setState();/(column|bar|pie)/.test(b.type)||e.insertAtFront(a);b.setState("hover");d.hoverSeries=
                                b
                                }
                            },
                        onmouseout:function(){
                            var q=d.hoverSeries;q&&c.events.mouseOut&&na(q,"mouseOut")
                            }
                        });if(c.cursor=="pointer")a.href="javascript:;";e.insertAtFront(a);g.push(a)
                    });b.areas=g
                }
            }
        };var cd=hb(Ma),dd=hb(Ma,{
        type:"area"
    }),Rc=hb(Ma,{
        type:"spline",
        translate:function(){
            var a=this;Ma.prototype.translate.apply(a,arguments);a.splinedata=a.getSplineData()
            },
        drawLine:function(){
            var a=this,b=a.segments;a.segments=a.splinedata;Ma.prototype.drawLine.apply(a,arguments);a.segments=b
            },
        getSplineData:function(){
            var a=this,
            b=a.chart,c=[],d;p(a.segments,function(e){
                if(a.xAxis.reversed)e=e.reverse();var f=[],g,i;p(e,function(j,k){
                    g=e[k+2]||e[k+1]||j;i=e[k-2]||e[k-1]||j;g.plotX>0&&i.plotY<b.plotWidth&&f.push(j)
                    });if(f.length>1)d=P(Zb(b.plotWidth,f[f.length-1].clientX-f[0].clientX)/3);c.push(e.length>1?d?(new Sc(f)).get(d):[]:e)
                });return a.splinedata=c
            }
        });Sc.prototype={
        get:function(a){
            a||(a=50);var b=this.n;b=(this.xdata[b-1]-this.xdata[0])/(a-1);var c=[],d=[];c[0]=this.xdata[0];d[0]=this.ydata[0];for(var e=[{
                plotX:c[0],
                plotY:d[0]
                }],f=1;f<a;f++){
                c[f]=c[0]+f*b;d[f]=this.interpolate(c[f]);e[f]={
                    plotX:c[f],
                    plotY:d[f]
                    }
                }return e
            },
        interpolate:function(a){
            for(var b=this.n-1,c=0;b-c>1;){
                var d=(b+c)/2;if(this.xdata[Va(d)]>a)b=d;else c=d
                    }b=Va(b);c=Va(c);d=this.xdata[b]-this.xdata[c];var e=(this.xdata[b]-a)/d;a=(a-this.xdata[c])/d;return e*this.ydata[c]+a*this.ydata[b]+((e*e*e-e)*this.y2[c]+(a*a*a-a)*this.y2[b])*d*d/6
            }
        };var ed=hb(Rc,{
        type:"areaspline"
    }),Qb=hb(Ma,{
        type:"column",
        init:function(){
            Ma.prototype.init.apply(this,arguments);
            var a=this,b=a.chart;b.hasRendered&&p(b.series,function(c){
                if(c.type==a.type)c.isDirty=true
                    })
            },
        translate:function(){
            var a=this,b=a.chart,c=0,d;Ma.prototype.translate.apply(a);p(b.series,function(D){
                if(D.type==a.type)if(D.options.stacking){
                    Za(d)||(d=c++);D.columnIndex=d
                    }else D.columnIndex=c++
            });var e=a.options,f=a.data,g=b.inverted,i=b.plotWidth,j=b.plotHeight,k=a.closestPoints;k=za(f[1]?f[k].plotX-f[k-1].plotX:g?j:i);var q=k*e.groupPadding,w=k-2*q;w=w/c;e=w*e.pointPadding;var y=w-2*e;b=(b.options.xAxis.reversed?
                c-a.columnIndex:a.columnIndex)||0;var H=-(k/2)+q+b*w+e,G=a.yAxis.translate(0);p(f,function(D){
                D.plotX+=H;D.w=y;D.y0=(g?i:j)-G;D.h=(D.yBottom||D.y0)-D.plotY
                })
            },
        drawLine:function(){},
        getSymbol:function(){},
        drawPoints:function(a){
            var b=this,c=b.options,d=b.chart,e=c.animation&&b.animate,f=d.inverted,g=b.data,i=b.stateLayers[a];e&&this.animate(true);p(g,function(j){
                if(j.plotY!==ma)i.drawRect(f?j.h>=0?d.plotWidth-j.plotY-j.h:d.plotWidth-j.plotY:j.plotX,f?d.plotHeight-j.plotX-j.w:j.h>=0?j.plotY:j.plotY+
                    j.h,f?za(j.h):j.w,f?j.w:za(j.h),c.borderColor,c.borderWidth,c.borderRadius,j.color||b.color,c.shadow);j.selected&&b.drawPointState(j,"select",i)
                });e&&b.animate()
            },
        drawPointState:function(a,b,c){
            var d=this,e=d.chart,f=d.options,g=a?a.options:null,i=e.inverted;c=c||d.singlePointLayer;if(b=="hover"){
                if(!c)c=d.singlePointLayer=new qa("single-point",d.layerGroup.div);c.clear()
                }if(b&&this.options.states[b]){
                b=Y(f,f.states[b],g);c.drawRect(i?e.plotWidth-a.plotY-a.h:a.plotX,i?e.plotHeight-a.plotX-a.w:a.plotY,
                    i?a.h:a.w,i?a.w:a.h,b.borderColor,b.borderWidth,b.borderRadius,zc(b.color||this.color).brighten(b.brightness).get(),b.shadow)
                }
            },
        getAreaCoords:function(){
            var a=[],b=this.chart,c=b.inverted;p(this.data,function(d){
                var e=Zb(za(d.h),3)*(d.h<0?-1:1),f=c?b.plotWidth-d.plotY-e:d.plotX,g=c?b.plotHeight-d.plotX-d.w:d.plotY,i=g+(c?d.w:e);e=f+(c?e:d.w);if(!c&&za(e-f)<1)e=f+1;else if(c&&za(g-i)<1)g=i+1;a.push([mb([f,i,f,g,e,g,e,i],P).join(","),d])
                });return a
            },
        cleanData:function(){
            var a=this,b=a.data,c,d,e,f;
            Ma.prototype.cleanData.apply(a);for(f=b.length-1;f>=0;f--)if(b[f-1]){
                c=b[f].x-b[f-1].x;if(d===ma||c<d){
                    d=c;e=f
                    }
                }a.closestPoints=e
            },
        animate:function(a){
            var b=this,c=b.chart,d=c.inverted,e=b.layerGroup.div;if(a)e.style[d?"left":"top"]=(d?-c.plotWidth:c.plotHeight)+C;else{
                xb(e,c.inverted?{
                    left:0
                }:{
                    top:0
                });b.animate=null
                }
            },
        remove:function(){
            var a=this,b=a.chart;b.hasRendered&&p(b.series,function(c){
                if(c.type==a.type)c.isDirty=true
                    });Ma.prototype.remove.apply(a,arguments)
            }
        }),Gc=hb(Qb,{
        type:"bar",
        init:function(a){
            a.inverted=
            this.inverted=true;Qb.prototype.init.apply(this,arguments)
            }
        }),gd=hb(Ma,{
        type:"scatter",
        getAreaCoords:function(){
            var a=this.data,b=[];p(a,function(c){
                b.push([[P(c.plotX),P(c.plotY)].join(","),c])
                });return b
            },
        cleanData:function(){}
        });Q=hb(hc,{
        setState:function(a){
            this.series.drawPointState(this,a)
            },
        init:function(){
            hc.prototype.init.apply(this,arguments);var a=this,b=a.series,c=b.chart.options.colors;J(a,{
                visible:a.visible!==false,
                name:fa(a.name,"Slice"),
                color:a.color||c[vb++]
                });if(vb>=c.length)vb=0;
            if(!a.layer)a.layer=new qa("pie",b.layerGroup.div);b=function(){
                a.slice()
                };La(a,"select",b);La(a,"unselect",b);return a
            },
        setVisible:function(a){
            var b=this,c=b.layer,d=b.legendItem;(b.visible=a=a===ma?!b.visible:a)?c.show():c.hide();if(d){
                d.className=a?"":Hb;b.series.chart.legend.drawGraphics(true)
                }
            },
        slice:function(a,b){
            var c=this,d=c.series;b=fa(b,true);c.sliced=Za(a)?a:!c.sliced;d.isDirty=true;b&&d.chart.redraw()
            }
        });var fd=hb(Ma,{
        type:"pie",
        isCartesian:false,
        pointClass:Q,
        getColor:function(){},
        translate:function(){
            var a=
            0,b=this,c=-0.25,d=b.options,e=d.slicedOffset,f=d.center,g=b.chart;b=b.data;var i=2*xa.PI,j;f.push(d.size);f=mb(f,function(k,q){
                return/%$/.test(k)?g["plot"+(q?"Height":"Width")]*parseInt(k)/100:k
                });p(b,function(k){
                a+=k.y
                });p(b,function(k){
                j=a?k.y/a:0;k.start=c*i;c+=j;k.end=c*i;k.percentage=j*100;k.center=[f[0],f[1]];k.size=f[2];var q=(k.end+k.start)/2;k.centerSliced=mb([fc(q)*e+f[0],gc(q)*e+f[1]],P)
                });this.setTooltipPoints()
            },
        render:function(){
            this.drawPoints();this.drawDataLabels()
            },
        drawPoints:function(){
            var a=
            this;p(this.data,function(b){
                a.drawPoint(b,b.layer.getCtx(),b.color);b.visible===false&&b.setVisible(false);b.selected&&a.drawPointState(b,"select",b.layer)
                })
            },
        getSymbol:function(){},
        drawPointState:function(a,b,c){
            var d=this,e=d.options;if(a){
                c=c||a.stateLayer;if(b=="hover"){
                    if(!c)c=a.stateLayer=new qa("single-point",a.layer.div);c.clear()
                    }if(b&&d.options.states[b]){
                    b=Y(e,e.states[b]);this.drawPoint(a,c.getCtx(),b.color||a.color,b.brightness)
                    }
                }d.hoverPoint&&d.hoverPoint.stateLayer&&d.hoverPoint.stateLayer.clear();
            d.hoverPoint=a
            },
        drawPoint:function(a,b,c,d){
            var e=this.options,f=a.sliced?a.centerSliced:a.center,g=f[0];f=f[1];var i=a.size,j=e.borderWidth,k=Na&&a.percentage==100?a.start:a.end;if(a.y>0){
                b.fillStyle=Bb(zc(c).brighten(d).get(b),b);b.strokeStyle=e.borderColor;b.lineWidth=j;b.beginPath();b.moveTo(g,f);b.arc(g,f,i/2,a.start,k,false);b.lineTo(g,f);b.closePath();b.fill();j&&b.stroke()
                }
            },
        getAreaCoords:function(){
            var a=[];p(this.data,function(b){
                for(var c=b.center[0],d=b.center[1],e=b.size/2,f=b.start,
                    g=b.end,i=[],j=f;j;j+=0.25){
                    if(j>=g)j=g;i=i.concat([c+fc(j)*e,d+gc(j)*e]);if(j>=g)break
                }i=i.concat([c,d]);b.tooltipPos=[c+2*fc((f+g)/2)*e/3,d+2*gc((f+g)/2)*e/3];a.push([mb(i,P).join(","),b])
                });return a
            },
        clear:function(){
            p(this.data,function(a){
                a.layer.clear();a.dataLabelsLayer&&a.dataLabelsLayer.clear();a.stateLayer&&a.stateLayer.clear()
                })
            }
        });Highcharts={
        numberFormat:Vc,
        dateFormat:Bc,
        setOptions:Uc,
        Chart:Wc
    }
    })();
