import React,{Component} from 'react'
import './componentCarousel.css'

export default class ComponentCarousel extends Component{
    state ={
        img:'https://avatars2.githubusercontent.com/u/50005159?v=4',
        bol:true
    }

    componentDidMount() {
        const img1='https://avatars2.githubusercontent.com/u/50005159?v=4'
        const img2='https://avatars0.githubusercontent.com/u/14026100?v=4'
        setInterval(function () {
                if(this.state.bol){
                    this.setState({img:img2,bol:false})
                }else {
                    this.setState({img:img1,bol:true})
                }
            }.bind(this), 3000
        )
    }

    render() {
        const {img}=this.state
        const img1='https://avatars2.githubusercontent.com/u/50005159?v=4'
        const img2='https://avatars0.githubusercontent.com/u/14026100?v=4'
        return(
            <div id="carouselExampleCaptions" className="carousel slide" data-ride="carousel">
                <ol className="carousel-indicators">
                    <li data-target="#carouselExampleCaptions" data-slide-to="0" className="active"></li>
                    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                </ol>
                <div className="carousel-inner">
                    <div className="carousel-item active text-center w-auto">
                        <img src={img1} className="d-block w-100" alt="..."/>
                            <div className="carousel-caption d-none d-md-block">
                                <h5>First slide label</h5>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </div>
                    </div>
                    <div className="carousel-item">
                        <img src={img1} className="d-block w-auto" alt="..."/>
                            <div className="carousel-caption d-none d-md-block">
                                <h5>Second slide label</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                    </div>
                    <div className="carousel-item">
                        <img src={img2} className="d-block w-auto" alt="..."/>
                            <div className="carousel-caption d-none d-md-block">
                                <h5>Third slide label</h5>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                    </div>
                </div>
                <a className="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="sr-only">Previous</span>
                </a>
                <a className="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="sr-only">Next</span>
                </a>
            </div>
        )
    }
}